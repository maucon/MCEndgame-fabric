package de.fuballer.mcendgame.main.mixin.damage;

import de.fuballer.mcendgame.main.accessor.LivingEntityDamageAccessor;
import de.fuballer.mcendgame.main.component.damage.DamageService;
import de.fuballer.mcendgame.main.component.damage.DifficultyScaling;
import de.fuballer.mcendgame.main.mixin.access.EntityAccessMixin;
import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedList;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityDamageCalculationMixin extends LivingEntity {
    protected EnderDragonEntityDamageCalculationMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    private float damageDuringSitting;
    @Final
    @Shadow
    public EnderDragonPart head;
    @Final
    @Shadow
    private PhaseManager phaseManager;

    @Inject(at = @At("HEAD"), method = "damagePart", cancellable = true)
    protected void damagePart(ServerWorld world, EnderDragonPart part, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (this.phaseManager.getCurrent().getType() == PhaseType.DYING) {
            cir.setReturnValue(false);
        } else {
            amount = this.phaseManager.getCurrent().modifyDamageTaken(source, amount);

            //////////////////////////////////////////////////////////////////////////////////////
            boolean damageReduction = false;
            //////////////////////////////////////////////////////////////////////////////////////

            if (part != this.head) {
                amount = amount / 4.0F + Math.min(amount, 1.0F);

                //////////////////////////////////////////////////////////////////////////////////////
                damageReduction = true;
                //////////////////////////////////////////////////////////////////////////////////////
            }

            if (amount < 0.01F) {
                cir.setReturnValue(false);
            } else {
                if (source.getAttacker() instanceof PlayerEntity || source.isIn(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS)) {
                    float f = this.getHealth();
                    // parentDamage(world, source, amount);

                    //////////////////////////////////////////////////////////////////////////////////////
                    callDamage(world, source, amount, damageReduction);
                    //////////////////////////////////////////////////////////////////////////////////////

                    if (this.isDead() && !this.phaseManager.getCurrent().isSittingOrHovering()) {
                        this.setHealth(1.0F);
                        this.phaseManager.setPhase(PhaseType.DYING);
                    }

                    if (this.phaseManager.getCurrent().isSittingOrHovering()) {
                        this.damageDuringSitting = this.damageDuringSitting + f - this.getHealth();
                        if (this.damageDuringSitting > 0.25F * this.getMaxHealth()) {
                            this.damageDuringSitting = 0.0F;
                            this.phaseManager.setPhase(PhaseType.TAKEOFF);
                        }
                    }
                }

                cir.setReturnValue(true);
            }
        }
    }

    // semantic copy of LivingEntityDamageMixin::callDamage
    @Unique
    private boolean callDamage(ServerWorld world, DamageSource source, float amount, boolean damageReduction) {
        LivingEntity this_ = (LivingEntity) (Object) this;

        var vanillaMoreDamage = new LinkedList<Double>();
        var vanillaMoreDamageTaken = new LinkedList<Double>();
        var difficultyScaling = DifficultyScaling.NONE;
        var armadilloDamageReduction = false;

        Entity entity;
        boolean bl3;
        boolean bl;
        if (this_.isInvulnerableTo(world, source)) {
            return false;
        }
        if (this_.isDead()) {
            return false;
        }
        if (source.isIn(DamageTypeTags.IS_FIRE) && this_.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
            return false;
        }
        if (this_.isSleeping()) {
            this_.wakeUp();
        }
        this.despawnCounter = 0;
        if (amount < 0.0f) {
            amount = 0.0f;
        }
        float f = amount;

        float blockedAmount = this_.getDamageBlockedAmount(world, source, amount);
        amount -= blockedAmount;
        boolean bl2 = bl = blockedAmount > 0.0f;
        ///////////////////////////////////////////////////////////////////////////////////
        var shieldBlocked = bl && this_.getActiveItem().get(DataComponentTypes.BLOCKS_ATTACKS) != null;
        ///////////////////////////////////////////////////////////////////////////////////

        if (source.isIn(DamageTypeTags.IS_FREEZING) && this_.getType().isIn(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {
            amount *= 5.0f;
            ///////////////////////////////////////////////////////////////////////////////////
            vanillaMoreDamage.add(5.0);
            ///////////////////////////////////////////////////////////////////////////////////
        }
        if (source.isIn(DamageTypeTags.DAMAGES_HELMET) && !this_.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
            this_.damageHelmet(source, amount);
            amount *= 0.75f;
            ///////////////////////////////////////////////////////////////////////////////////
            vanillaMoreDamage.add(0.75);
            ///////////////////////////////////////////////////////////////////////////////////
        }

        if (Float.isNaN(amount) || Float.isInfinite(amount)) {
            amount = Float.MAX_VALUE;
        }

        ///////////////////////////////////////////////////////////////////////////////////
        amount = DamageService.INSTANCE.calculateFinalDamage(
                this_,
                world,
                source,
                shieldBlocked,
                difficultyScaling,
                vanillaMoreDamage,
                vanillaMoreDamageTaken,
                armadilloDamageReduction,
                damageReduction,
                amount
        );
        ///////////////////////////////////////////////////////////////////////////////////

        boolean bl22 = true;
        if ((float) this_.timeUntilRegen > 10.0f && !source.isIn(DamageTypeTags.BYPASSES_COOLDOWN)) {
            if (amount <= this.lastDamageTaken) {
                return false;
            }
            this.applyDamage(world, source, amount - this.lastDamageTaken);
            this.lastDamageTaken = amount;
            bl22 = false;
        } else {
            this.lastDamageTaken = amount;
            this_.timeUntilRegen = 20;
            this.applyDamage(world, source, amount);
            this_.hurtTime = this_.maxHurtTime = 10;
        }

        this.becomeAngry(source);
        this.setAttackingPlayer(source);

        if (bl22) {
            BlocksAttacksComponent blocksAttacksComponent = this_.getActiveItem().get(DataComponentTypes.BLOCKS_ATTACKS);
            if (bl && blocksAttacksComponent != null) {
                blocksAttacksComponent.playBlockSound(world, this_);
            } else {
                world.sendEntityDamage(this_, source);
            }
            if (!(source.isIn(DamageTypeTags.NO_IMPACT) || bl && !(amount > 0.0f))) {
                ((EntityAccessMixin) this_).invokeScheduleVelocityUpdate();
            }
            if (!source.isIn(DamageTypeTags.NO_KNOCKBACK)) {
                double d = 0.0;
                double e = 0.0;
                Entity entity2 = source.getSource();
                if (entity2 instanceof ProjectileEntity projectileEntity) {
                    DoubleDoubleImmutablePair doubleDoubleImmutablePair = projectileEntity.getKnockback(this_, source);
                    d = -doubleDoubleImmutablePair.leftDouble();
                    e = -doubleDoubleImmutablePair.rightDouble();
                } else if (source.getPosition() != null) {
                    d = source.getPosition().getX() - this_.getX();
                    e = source.getPosition().getZ() - this_.getZ();
                }
                this_.takeKnockback(0.4f, d, e);
                if (!bl) {
                    this_.tiltScreen(d, e);
                }
            }
        }
        if (this_.isDead()) {
            if (!((LivingEntityDamageAccessor) this).mcendgame$tryUseDeathProtector(source)) {
                if (bl22) {
                    this_.playSound(this.getDeathSound());
                    ((LivingEntityDamageAccessor) this).mcendgame$playThornsSound(source);
                }
                this_.onDeath(source);
            }
        } else if (bl22) {
            this.playHurtSound(source);
            ((LivingEntityDamageAccessor) this).mcendgame$playThornsSound(source);
        }
        boolean bl4 = bl3 = !bl || amount > 0.0f;
        if (bl3) {
            ((LivingEntityDamageAccessor) this).mcendgame$setLastDamageSource(source);
            ((LivingEntityDamageAccessor) this).mcendgame$setLastDamageTime(this_.getWorld().getTime());
            for (StatusEffectInstance statusEffectInstance : this_.getStatusEffects()) {
                statusEffectInstance.onEntityDamage(world, this_, source, amount);
            }
        }

        if ((entity = source.getAttacker()) instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) entity;
            Criteria.PLAYER_HURT_ENTITY.trigger(serverPlayerEntity, this_, source, f, amount, bl);
        }
        return bl3;
    }
}
