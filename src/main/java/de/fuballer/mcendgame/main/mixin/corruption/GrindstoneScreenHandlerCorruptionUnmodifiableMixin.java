package de.fuballer.mcendgame.main.mixin.corruption;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.fuballer.mcendgame.main.messaging.misc.GrindstoneOutputCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerCorruptionUnmodifiableMixin {
    @ModifyReturnValue(method = "getOutputStack", at = @At("RETURN"))
    ItemStack getOutputStack(
            ItemStack originalOutput,
            ItemStack firstInput,
            ItemStack secondInput
    ) {
        var canUseGrindstoneCommand = new GrindstoneOutputCommand(firstInput, secondInput, originalOutput);
        var cmd = CommandGateway.INSTANCE.apply(canUseGrindstoneCommand);
        return cmd.getOutput();
    }
}
