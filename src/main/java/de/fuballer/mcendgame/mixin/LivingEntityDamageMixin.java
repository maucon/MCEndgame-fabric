package de.fuballer.mcendgame.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityDamageMixin {
    @Inject(at = @At("HEAD"), method = "damage")
    private void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        LivingEntity entity = (LivingEntity) (Object) this;
        System.out.println(source.getName() + " deals " + amount + " damage to " + entity);
    }
}
