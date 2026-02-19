package de.fuballer.mcendgame.main.mixin.status_effect;

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.entity.effect.InstantHealthOrDamageStatusEffect")
public class ApplyHealingFactorInstantHealthOrDamageStatusEffectMixin {
    @Redirect(
            method = "applyInstantEffect",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;heal(F)V")
    )
    void modifyHeal(
            LivingEntity instance,
            float originalAmount,
            ServerWorld world,
            Entity effectEntity,
            Entity attacker,
            LivingEntity target,
            int amplifier,
            double proximity
    ) {
        if (!(attacker instanceof LivingEntity livingAttacker)) {
            target.heal(originalAmount);
            return;
        }

        var healingFactor = CustomAttributesExtensions.INSTANCE.getHealingFactor(livingAttacker);
        target.heal(originalAmount * (float) healingFactor);
    }
}
