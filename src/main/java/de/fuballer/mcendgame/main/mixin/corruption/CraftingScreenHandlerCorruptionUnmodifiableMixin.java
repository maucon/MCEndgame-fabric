package de.fuballer.mcendgame.main.mixin.corruption;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.messaging.misc.CraftingResultCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.screen.CraftingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerCorruptionUnmodifiableMixin {
    @ModifyVariable(method = "updateResult",
            at = @At(value = "STORE"),
            ordinal = 1
    )
    private static ItemStack updateResult(
            ItemStack originalStack,
            @Local CraftingRecipeInput craftingRecipeInput
    ) {
        var recipeCommand = new CraftingResultCommand(craftingRecipeInput, originalStack);
        var cmd = CommandGateway.INSTANCE.apply(recipeCommand);
        return cmd.getResult();
    }
}
