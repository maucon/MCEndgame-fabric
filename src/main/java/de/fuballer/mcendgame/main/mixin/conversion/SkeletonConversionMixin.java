package de.fuballer.mcendgame.main.mixin.conversion;

import de.fuballer.mcendgame.main.messaging.misc.EntityConversionCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.entity.mob.SkeletonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkeletonEntity.class)
public class SkeletonConversionMixin {
    @Inject(method = "setConverting", at = @At("HEAD"), cancellable = true)
    void setConverting(boolean converting, CallbackInfo ci) {
        var entity = (SkeletonEntity) (Object) this;
        var entityConversionCommand = EntityConversionCommand.Companion.of(entity);
        var cmd = CommandGateway.INSTANCE.apply(entityConversionCommand);

        if (cmd.getCanConvert()) return;

        ci.cancel();
    }
}
