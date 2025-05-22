package de.fuballer.mcendgame.main.mixin.status_effect;

import de.fuballer.mcendgame.main.accessor.LivingEntityWitherDamageImmunityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.WitherStatusEffect;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherStatusEffect.class)
public class WitherStatusEffectMixin {
    @Inject(method = "applyUpdateEffect", at = @At("HEAD"), cancellable = true)
    void applyUpdateEffect(
            ServerWorld world,
            LivingEntity entity,
            int amplifier,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (((LivingEntityWitherDamageImmunityAccessor) entity).mcendgame$isImmuneToWitherDamage()) cir.setReturnValue(true);
    }
}
