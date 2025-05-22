package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamageCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityDamageCommandMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        var entity = (LivingEntity) (Object) this;
        var livingEntityDamageCommand = LivingEntityDamageCommand.Companion.of(entity, source, amount);
        var cmd = CommandGateway.INSTANCE.apply(livingEntityDamageCommand);

        if (cmd.getDealsDamage()) return;

        cir.setReturnValue(false);
    }
}
