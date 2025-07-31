package de.fuballer.mcendgame.main.mixin.knockback;

import de.fuballer.mcendgame.main.component.custom_attribute.effects.knockback.AttackKnockbackUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.RamImpactTask;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RamImpactTask.class)
public class RamImpactTaskKnockbackCommandMixin {
    @Redirect(
            method = "keepRunning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/GoatEntity;J)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;takeKnockback(DDD)V")
    )
    void redirectTakeKnockback(LivingEntity instance, double strength, double x, double z, ServerWorld serverWorld, GoatEntity goatEntity) {
        AttackKnockbackUtil.INSTANCE.takeKnockbackFrom(instance, goatEntity, strength, x, z);
    }
}
