package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.messaging.misc.GainStatusEffectCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class LivingEntityChangeGainedStatusEffectMixin {
    @ModifyVariable(
            method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z",
            at = @At("HEAD"),
            argsOnly = true
    )
    private StatusEffectInstance modifyGainedStatusEffect(StatusEffectInstance originalEffect) {
        var entity = (LivingEntity) (Object) this;
        var command = new GainStatusEffectCommand(entity, originalEffect);
        var cmd = CommandGateway.INSTANCE.apply(command);
        return cmd.getEffect();
    }
}
