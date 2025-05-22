package de.fuballer.mcendgame.main.mixin.conversion;

import de.fuballer.mcendgame.main.messaging.misc.EntityConversionCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public class ZombieConversionMixin {
    @Inject(method = "canConvertInWater", at = @At("HEAD"), cancellable = true)
    void canConvertInWater(CallbackInfoReturnable<Boolean> cir) {
        var entity = (ZombieEntity) (Object) this;
        var entityConversionCommand = EntityConversionCommand.Companion.of(entity);
        var cmd = CommandGateway.INSTANCE.apply(entityConversionCommand);

        if (cmd.getCanConvert()) return;

        cir.setReturnValue(false);
    }
}
