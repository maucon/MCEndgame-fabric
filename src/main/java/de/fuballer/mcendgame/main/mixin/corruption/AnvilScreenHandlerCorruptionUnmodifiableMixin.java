package de.fuballer.mcendgame.main.mixin.corruption;

import de.fuballer.mcendgame.main.messaging.misc.CanAnvilForgeCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerCorruptionUnmodifiableMixin {
    @Shadow
    @Final
    private Property levelCost;

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    void on(CallbackInfo ci) {
        var accessor = (ForgingScreenHandlerOutputAccessorMixin) this;

        var input = accessor.getInput();
        var stack0 = input.getStack(0);
        var stack1 = input.getStack(1);

        var anvilInputCommand = new CanAnvilForgeCommand(stack0, stack1, true);
        var cmd = CommandGateway.INSTANCE.apply(anvilInputCommand);
        if (cmd.getCanForge()) return;

        var output = accessor.getOutput();
        output.setStack(0, ItemStack.EMPTY);
        levelCost.set(0);

        ci.cancel();
    }
}
