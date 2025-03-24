package de.fuballer.mcendgame.mixin;

import de.fuballer.mcendgame.accessors.PlayerCooldownAccessor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityDamageMixin implements PlayerCooldownAccessor {
    @Shadow
    protected abstract float getDamageAgainst(Entity target, float baseDamage, DamageSource damageSource);

    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Unique
    private float attackCooldown;

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getAttackCooldownProgress(F)F"))
    protected void applyDamage(Entity target, CallbackInfo ci) {
        var player = (PlayerEntity) (Object) this;
        if (!(player instanceof ServerPlayerEntity)) {
            return;
        }

        attackCooldown = player.getAttackCooldownProgress(0.5F);
    }

    @Inject(at = @At("HEAD"), method = "attack", cancellable = true)
    public void attack(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        LivingEntityAccessor livingEntityAccessor = (LivingEntityAccessor) player;
        World world = player.getWorld();

        if (!target.isAttackable()) {
            ci.cancel();
            return;
        }
        if (target.handleAttack(player)) {
            ci.cancel();
            return;
        }
        if (isReflectingProjectile(target, player)) {
            ci.cancel();
            return;
        }

        float baseDamage = player.isUsingRiptide() ? livingEntityAccessor.getRiptideAttackDamage()
                : (float) player.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);

        ItemStack weaponItemStack = player.getWeaponStack();
        DamageSource damageSource = Optional.ofNullable(weaponItemStack.getItem().getDamageSource(player)).orElse(player.getDamageSources().playerAttack(player));

        float additionalEnchantmentDamage = this.getDamageAgainst(target, baseDamage, damageSource) - baseDamage;

        float attackCharge = player.getAttackCooldownProgress(0.5F);
        player.resetLastAttackedTicks();
        float unenchantedDamage = baseDamage * (0.2F + attackCharge * attackCharge * 0.8F);
        additionalEnchantmentDamage *= attackCharge;

        if (unenchantedDamage <= 0.0F && additionalEnchantmentDamage <= 0.0F) {
            ci.cancel();
            return;
        }

        unenchantedDamage += weaponItemStack.getItem().getBonusAttackDamage(target, unenchantedDamage, damageSource); //currently only mace unenchantedDamage (1.21.5)

        boolean isCharged = attackCharge > 0.9F;
        boolean isCritical = isCharged
                && player.fallDistance > 0.0F
                && !player.isOnGround()
                && !player.isClimbing()
                && !player.isTouchingWater()
                && !player.hasStatusEffect(StatusEffects.BLINDNESS)
                && !player.hasVehicle()
                && target instanceof LivingEntity
                && !player.isSprinting();
        if (isCritical) {
            unenchantedDamage *= 1.5F;
        }

        float combinedDamage = unenchantedDamage + additionalEnchantmentDamage;

        boolean shouldApplySprintKnockback = player.isSprinting() && isCharged;
        if (shouldApplySprintKnockback) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
        }

        boolean isSweeping = false;
        if (isCharged && !isCritical && !shouldApplySprintKnockback && player.isOnGround()) {
            double currentVelocity = player.getMovement().horizontalLength();
            double sprintingVelocity = (double) player.getMovementSpeed() * 2.5;
            if (currentVelocity < sprintingVelocity && player.getStackInHand(Hand.MAIN_HAND).isIn(ItemTags.SWORDS)) {
                isSweeping = true;
            }
        }

        float targetHealth = 0.0F;
        if (target instanceof LivingEntity livingEntity) {
            targetHealth = livingEntity.getHealth();
        }

        boolean dealsDamage = target.sidedDamage(damageSource, combinedDamage);
        if (!dealsDamage) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);
            ci.cancel();
            return;
        }

        Vec3d targetVelocity = target.getVelocity();
        float knockbackStrength = livingEntityAccessor.getKnockbackAgainst(target, damageSource) + (shouldApplySprintKnockback ? 1.0F : 0.0F);
        if (knockbackStrength > 0.0F) {
            if (target instanceof LivingEntity livingEntity) {
                livingEntity.takeKnockback(
                        (knockbackStrength * 0.5F),
                        MathHelper.sin(player.getYaw() * (float) (Math.PI / 180.0)),
                        (-MathHelper.cos(player.getYaw() * (float) (Math.PI / 180.0)))
                );
            } else {
                target.addVelocity(
                        (-MathHelper.sin(player.getYaw() * (float) (Math.PI / 180.0)) * knockbackStrength * 0.5F),
                        0.1,
                        (MathHelper.cos(player.getYaw() * (float) (Math.PI / 180.0)) * knockbackStrength * 0.5F)
                );
            }

            player.setVelocity(player.getVelocity().multiply(0.6, 1.0, 0.6));
            player.setSprinting(false);
        }

        if (isSweeping) {
            float sweepingDamage = 1.0F + (float) player.getAttributeValue(EntityAttributes.SWEEPING_DAMAGE_RATIO) * unenchantedDamage;

            for (LivingEntity livingEntity : world.getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0, 0.25, 1.0))) {
                if (livingEntity == player || livingEntity == target) continue;
                if (player.isTeammate(livingEntity)) continue;
                if ((livingEntity instanceof ArmorStandEntity) && ((ArmorStandEntity) livingEntity).isMarker()) continue;
                if (player.squaredDistanceTo(livingEntity) >= 9.0) continue;

                float enchantedSweepingDamage = this.getDamageAgainst(livingEntity, sweepingDamage, damageSource) * attackCharge;
                livingEntity.takeKnockback(0.4F, MathHelper.sin(player.getYaw() * (float) (Math.PI / 180.0)), (-MathHelper.cos(player.getYaw() * (float) (Math.PI / 180.0))));

                livingEntity.serverDamage(damageSource, enchantedSweepingDamage);

                if (world instanceof ServerWorld serverWorld) {
                    EnchantmentHelper.onTargetDamaged(serverWorld, livingEntity, damageSource);
                }
            }

            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
            player.spawnSweepAttackParticles();
        }

        if (target instanceof ServerPlayerEntity && target.velocityModified) {
            ((ServerPlayerEntity) target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));
            target.velocityModified = false;
            target.setVelocity(targetVelocity);
        }

        if (isCritical) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
            player.addCritParticles(target);
        }

        if (!isCritical && !isSweeping) {
            if (isCharged) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
            } else {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, player.getSoundCategory(), 1.0F, 1.0F);
            }
        }

        if (additionalEnchantmentDamage > 0.0F) {
            player.addEnchantedHitParticles(target);
        }

        player.onAttacking(target);
        Entity entity = target;
        if (target instanceof EnderDragonPart) {
            entity = ((EnderDragonPart) target).owner;
        }

        boolean wasItemUsed = false;
        if (world instanceof ServerWorld serverWorld) {
            if (entity instanceof LivingEntity livingEntity) {
                wasItemUsed = weaponItemStack.postHit(livingEntity, player);
            }

            EnchantmentHelper.onTargetDamaged(serverWorld, target, damageSource);
        }

        if (!world.isClient && !weaponItemStack.isEmpty() && entity instanceof LivingEntity) {
            if (wasItemUsed) {
                weaponItemStack.postDamageEntity((LivingEntity) entity, player);
            }

            if (weaponItemStack.isEmpty()) {
                if (weaponItemStack == player.getMainHandStack()) {
                    player.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                } else {
                    player.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                }
            }
        }

        if (target instanceof LivingEntity) {
            float dealtDamage = targetHealth - ((LivingEntity) target).getHealth();
            player.increaseStat(Stats.DAMAGE_DEALT, Math.round(dealtDamage * 10.0F));

            if (world instanceof ServerWorld && dealtDamage > 2.0F) {
                int heartsDealt = (int) ((double) dealtDamage * 0.5);
                ((ServerWorld) world)
                        .spawnParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getBodyY(0.5), target.getZ(), heartsDealt, 0.1, 0.0, 0.1, 0.2);
            }
        }

        player.addExhaustion(0.1F);

        ci.cancel();
    }

    @Unique
    private boolean isReflectingProjectile(Entity target, PlayerEntity player) {
        if (!target.getType().isIn(EntityTypeTags.REDIRECTABLE_PROJECTILE)) return false;
        if (!(target instanceof ProjectileEntity projectileEntity)) return false;
        if (!projectileEntity.deflect(ProjectileDeflection.REDIRECTED, player, player, true)) return false;

        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory());
        return true;
    }

    @Override
    public float mcendgame$getAttackCooldown() {
        return attackCooldown;
    }
}
