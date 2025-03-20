package de.fuballer.mcendgame.mixin;

import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationEvent;
import de.fuballer.mcendgame.components.damage.ApplyDamageUtil;
import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
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
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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

    @Shadow
    private @Nullable LivingEntity attacker;

    @Inject(at = @At("HEAD"), method = "applyDamage", cancellable = true)
    protected void applyDamage(
            ServerWorld world,
            DamageSource source,
            float originalDamage,
            CallbackInfo ci
    ) {
        LivingEntity entity = (LivingEntity) (Object) this;

        var event = ApplyDamageCalculationEvent.Companion.of(entity, world, source, originalDamage);
        ApplyDamageCalculationEvent.Companion.getNOTIFIER().interact(event);

        if (entity.isInvulnerableTo(world, source)) {
            ci.cancel();
            return;
        }

        var attackDamage = ApplyDamageUtil.INSTANCE.calculateAttackDamage(originalDamage, source, event);
        attackDamage = mcendgame$applyArmorToDamage(attackDamage, source, event, entity);
        attackDamage = mcendgame$modifyAppliedDamage(source, attackDamage, entity);

        var elementalDamage = ApplyDamageUtil.INSTANCE.calculateElementalDamage(source, event);
        elementalDamage = mcendgame$applyWardToDamage(elementalDamage, source, event, entity);
        elementalDamage = mcendgame$modifyAppliedDamage(source, elementalDamage, entity);

        var combinedDamage = attackDamage + elementalDamage;

        System.out.println("-----------");
        System.out.println("ATTACK:    " + attackDamage);
        System.out.println("ELEMENTAL: " + elementalDamage);
        System.out.println("TOTAL:     " + combinedDamage);

        float healthDamage = Math.max(combinedDamage - entity.getAbsorptionAmount(), 0.0F);
        float absorbedDamage = combinedDamage - healthDamage;
        entity.setAbsorptionAmount(entity.getAbsorptionAmount() - absorbedDamage);
        if (absorbedDamage > 0.0F && absorbedDamage < 3.4028235E37F && source.getAttacker() instanceof ServerPlayerEntity serverPlayerEntity) {
            serverPlayerEntity.increaseStat(Stats.DAMAGE_DEALT_ABSORBED, Math.round(absorbedDamage * 10.0F));
        }

        if (healthDamage != 0.0F) {
            entity.getDamageTracker().onDamage(source, healthDamage);
            entity.setHealth(entity.getHealth() - healthDamage);
            entity.emitGameEvent(GameEvent.ENTITY_DAMAGE);
        }

        ci.cancel();
    }

    @Unique
    private float mcendgame$applyArmorToDamage(
            float amount,
            DamageSource source,
            ApplyDamageCalculationEvent event,
            LivingEntity entity
    ) {
        if (source.isIn(DamageTypeTags.BYPASSES_ARMOR)) return amount;
        entity.damageArmor(source, amount);

        var armor = entity.getArmor();
        var armorToughness = (float) entity.getAttributeValue(EntityAttributes.ARMOR_TOUGHNESS);
        amount = ApplyDamageUtil.INSTANCE.reduceAttackDamageByArmor(entity, amount, source, armor, armorToughness);

        return amount;
    }

    @Unique
    private float mcendgame$applyWardToDamage(
            float amount,
            DamageSource source,
            ApplyDamageCalculationEvent event,
            LivingEntity entity
    ) {
        //if (source.isIn(DamageTypeTags.BYPASSES_ARMOR)) return amount; //TODO DECIDE IF APPLY
        //entity.damageArmor(source, amount);

        var ward = event.getWard().stream().mapToDouble(Double::doubleValue).sum();
        amount = ApplyDamageUtil.INSTANCE.reduceElementalDamageByWard(entity, amount, source, (float) ward);

        return amount;
    }

    @Unique
    protected float mcendgame$modifyAppliedDamage(
            DamageSource source,
            float amount,
            LivingEntity entity
    ) {
        if (source.isIn(DamageTypeTags.BYPASSES_EFFECTS)) return amount;

        if (entity.hasStatusEffect(StatusEffects.RESISTANCE) && !source.isIn(DamageTypeTags.BYPASSES_RESISTANCE)) {
            int resistance = entity.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() + 1;

            float resistancePercent = resistance * 0.2F;
            float resistedDamage = Math.min(amount * resistancePercent, amount);
            amount -= resistedDamage;

            if (resistedDamage > 0.0F && resistedDamage < 3.4028235E37F) {
                if (entity instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) entity).increaseStat(Stats.DAMAGE_RESISTED, Math.round(resistedDamage * 10.0F));
                } else if (source.getAttacker() instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) source.getAttacker()).increaseStat(Stats.DAMAGE_DEALT_RESISTED, Math.round(resistedDamage * 10.0F));
                }
            }
        }

        if (amount <= 0.0F) return 0.0F;
        if (source.isIn(DamageTypeTags.BYPASSES_ENCHANTMENTS)) return amount;
        if (!(entity.getWorld() instanceof ServerWorld serverWorld)) return amount;

        var protectionAmount = EnchantmentHelper.getProtectionAmount(serverWorld, entity, source);
        amount = DamageUtil.getInflictedDamage(amount, protectionAmount);
        return amount;
    }

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private void damage(
            ServerWorld world,
            DamageSource source,
            float amount,
            CallbackInfoReturnable<Boolean> info
    ) {
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
