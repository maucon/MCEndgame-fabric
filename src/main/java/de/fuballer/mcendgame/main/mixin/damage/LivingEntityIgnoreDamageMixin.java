package de.fuballer.mcendgame.main.mixin.damage;

import de.fuballer.mcendgame.main.messaging.misc.LivingEntityIgnoreDamageCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityIgnoreDamageMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true, order = 900)
    void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        var entity = (LivingEntity) (Object) this;

        var cmd = LivingEntityIgnoreDamageCommand.Companion.of(entity, source, amount);
        cmd = CommandGateway.INSTANCE.apply(cmd);

        if (cmd.getIgnoresDamage()) {
            cir.setReturnValue(false);
        }
    }
}
