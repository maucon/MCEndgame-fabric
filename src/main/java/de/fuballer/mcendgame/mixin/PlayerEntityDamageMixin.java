package de.fuballer.mcendgame.mixin;

import de.fuballer.mcendgame.accessors.PlayerCooldownAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityDamageMixin implements PlayerCooldownAccessor {

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

    @Override
    public float mcendgame$getAttackCooldown() {
        return attackCooldown;
    }
}
