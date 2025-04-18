package de.fuballer.mcendgame.mixin;

import de.fuballer.mcendgame.accessor.PlayerEntityMixinAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityDamageMixin extends LivingEntity implements PlayerEntityMixinAccessor {
    @Unique
    private float lastAttackCharge;
    @Unique
    private boolean lastAttackWasCritical;

    protected PlayerEntityDamageMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "applyDamage", cancellable = true)
    protected void applyDamage(
            ServerWorld world,
            DamageSource source,
            float amount,
            CallbackInfo ci
    ) {
        super.applyDamage(world, source, amount);
        ci.cancel();
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getAttackCooldownProgress(F)F"))
    protected void attack(Entity target, CallbackInfo ci) {
        var player = (PlayerEntity) (Object) this;
        if (!(player instanceof ServerPlayerEntity)) {
            return;
        }

        // TODO take from minecraft vars
        lastAttackCharge = player.getAttackCooldownProgress(0.5F);
        lastAttackWasCritical = lastAttackCharge > 0.9F
                && player.fallDistance > 0.0F
                && !player.isOnGround()
                && !player.isClimbing()
                && !player.isTouchingWater()
                && !player.hasStatusEffect(StatusEffects.BLINDNESS)
                && !player.hasVehicle()
                && target instanceof LivingEntity
                && !player.isSprinting();
    }

    @Override
    public float mcendgame$getLastAttackCharge() {
        return lastAttackCharge;
    }

    @Override
    public boolean mcendgame$getLastAttackWasCritical() {
        return lastAttackWasCritical;
    }
}
