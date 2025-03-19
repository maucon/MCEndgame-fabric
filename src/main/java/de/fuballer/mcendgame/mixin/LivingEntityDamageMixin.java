package de.fuballer.mcendgame.mixin;

import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationEvent;
import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDamageMixin {
    @Shadow
    protected float lastDamageTaken;
    @Shadow
    private DamageSource lastDamageSource;
    @Shadow
    private long lastDamageTime;

    @Shadow
    protected abstract void takeShieldHit(LivingEntity attacker);

    @Shadow
    protected abstract void applyDamage(ServerWorld world, DamageSource source, float amount);

    @Shadow
    protected abstract void becomeAngry(DamageSource damageSource);

    @Shadow
    protected abstract PlayerEntity setAttackingPlayer(DamageSource damageSource);

    @Shadow
    protected abstract boolean tryUseDeathProtector(DamageSource source);

    @Shadow
    protected abstract SoundEvent getDeathSound();

    @Shadow
    protected abstract void playHurtSound(DamageSource damageSource);

    @Shadow
    protected abstract float applyArmorToDamage(DamageSource source, float amount);

    @Shadow
    protected abstract float modifyAppliedDamage(DamageSource source, float amount);

    @Inject(at = @At("HEAD"), method = "applyDamage", cancellable = true)
    protected void applyDamage(ServerWorld world, DamageSource source, float amount, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        var event = ApplyDamageCalculationEvent.Companion.of(entity, world, source, amount);
        ApplyDamageCalculationEvent.Companion.getNOTIFIER().interact(event);

        System.out.println("amount before: " + amount);
        amount *= (float) (1 + (event.getIncreasedDamage().stream().mapToDouble(a -> a).sum()));
        System.out.println("amount after: " + amount);

        if (!entity.isInvulnerableTo(world, source)) {
            amount = this.applyArmorToDamage(source, amount);
            amount = this.modifyAppliedDamage(source, amount);
            float healthDamage = Math.max(amount - entity.getAbsorptionAmount(), 0.0F);
            entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (amount - healthDamage));
            float g = amount - healthDamage;
            if (g > 0.0F && g < 3.4028235E37F && source.getAttacker() instanceof ServerPlayerEntity serverPlayerEntity) {
                serverPlayerEntity.increaseStat(Stats.DAMAGE_DEALT_ABSORBED, Math.round(g * 10.0F));
            }

            if (healthDamage != 0.0F) {
                entity.getDamageTracker().onDamage(source, healthDamage);
                entity.setHealth(entity.getHealth() - healthDamage);
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() - healthDamage);
                entity.emitGameEvent(GameEvent.ENTITY_DAMAGE);
            }
        }

        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.isInvulnerableTo(world, source)) {
            info.setReturnValue(false);
            return;
        }
        if (entity.isDead()) {
            info.setReturnValue(false);
            return;
        }
        if (source.isIn(DamageTypeTags.IS_FIRE) && entity.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
            info.setReturnValue(false);
            return;
        }
        if (entity.isSleeping()) {
            entity.wakeUp();
        }

        entity.setDespawnCounter(0);
        if (amount < 0.0F) {
            amount = 0.0F;
        }

        float f = amount;
        boolean damageShieldBlocked = false;
        float blockedDamage = 0.0F;
        if (amount > 0.0F && entity.blockedByShield(source)) {
            entity.damageShield(amount);
            blockedDamage = amount;
            amount = 0.0F;
            if (!source.isIn(DamageTypeTags.IS_PROJECTILE) && source.getSource() instanceof LivingEntity livingEntity) {
                this.takeShieldHit(livingEntity);
            }

            damageShieldBlocked = true;
        }

        if (source.isIn(DamageTypeTags.IS_FREEZING) && entity.getType().isIn(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {
            amount *= 5.0F;
        }

        if (source.isIn(DamageTypeTags.DAMAGES_HELMET) && !entity.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
            entity.damageHelmet(source, amount);
            amount *= 0.75F;
        }

        entity.limbAnimator.setSpeed(1.5F);
        if (Float.isNaN(amount) || Float.isInfinite(amount)) {
            amount = Float.MAX_VALUE;
        }

        boolean inInvulnerabilityTime = true;
        if ((float) entity.timeUntilRegen > 10.0F && !source.isIn(DamageTypeTags.BYPASSES_COOLDOWN)) {
            if (amount <= this.lastDamageTaken) {
                info.setReturnValue(false);
                return;
            }

            this.applyDamage(world, source, amount - this.lastDamageTaken);
            this.lastDamageTaken = amount;
            inInvulnerabilityTime = false;
        } else {
            this.lastDamageTaken = amount;
            entity.timeUntilRegen = 20;
            this.applyDamage(world, source, amount);
            entity.maxHurtTime = 10;
            entity.hurtTime = entity.maxHurtTime;
        }

        this.becomeAngry(source);
        this.setAttackingPlayer(source);
        if (inInvulnerabilityTime) {
            if (damageShieldBlocked) {
                world.sendEntityStatus(entity, EntityStatuses.BLOCK_WITH_SHIELD);
            } else {
                world.sendEntityDamage(entity, source);
            }

            /*
            if (!source.isIn(DamageTypeTags.NO_IMPACT) && (!damageShieldBlocked || amount > 0.0F)) {
                ((EntityAccessor) entity).scheduleVelocityUpdate();
            }*/

            if (!source.isIn(DamageTypeTags.NO_KNOCKBACK)) {
                double d = 0.0;
                double e = 0.0;
                if (source.getSource() instanceof ProjectileEntity projectileEntity) {
                    DoubleDoubleImmutablePair doubleDoubleImmutablePair = projectileEntity.getKnockback(entity, source);
                    d = -doubleDoubleImmutablePair.leftDouble();
                    e = -doubleDoubleImmutablePair.rightDouble();
                } else if (source.getPosition() != null) {
                    d = source.getPosition().getX() - entity.getX();
                    e = source.getPosition().getZ() - entity.getZ();
                }

                entity.takeKnockback(0.4F, d, e);
                if (!damageShieldBlocked) {
                    entity.tiltScreen(d, e);
                }
            }
        }

        if (entity.isDead()) {
            if (!this.tryUseDeathProtector(source)) {
                if (inInvulnerabilityTime) {
                    entity.playSound(this.getDeathSound());
                }

                entity.onDeath(source);
            }
        } else if (inInvulnerabilityTime) {
            this.playHurtSound(source);
        }

        boolean shouldHit = !damageShieldBlocked || amount > 0.0F;
        if (shouldHit) {
            this.lastDamageSource = source;
            this.lastDamageTime = entity.getWorld().getTime();

            for (StatusEffectInstance statusEffectInstance : entity.getStatusEffects()) {
                statusEffectInstance.onEntityDamage(world, entity, source, amount);
            }
        }

        if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.ENTITY_HURT_PLAYER.trigger(serverPlayerEntity, source, f, amount, damageShieldBlocked);
            if (blockedDamage > 0.0F && blockedDamage < 3.4028235E37F) {
                serverPlayerEntity.increaseStat(Stats.DAMAGE_BLOCKED_BY_SHIELD, Math.round(blockedDamage * 10.0F));
            }
        }

        if (source.getAttacker() instanceof ServerPlayerEntity serverPlayerEntityx) {
            Criteria.PLAYER_HURT_ENTITY.trigger(serverPlayerEntityx, entity, source, f, amount, damageShieldBlocked);
        }

        info.setReturnValue(shouldHit);
    }
}
