package de.fuballer.mcendgame.main.mixin.corruption;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.messaging.misc.CraftingResultCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.screen.CrafterScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CrafterScreenHandler.class)
public class CrafterScreenHandlerCorruptionUnmodifiableMixin {
    @ModifyVariable(
            method = "updateResult",
            at = @At(value = "STORE"),
            ordinal = 0
    )
    ItemStack on(
            ItemStack originalStack,
            @Local CraftingRecipeInput recipeInput
    ) {
        var recipeCommand = new CraftingResultCommand(recipeInput, originalStack);
        var cmd = CommandGateway.INSTANCE.apply(recipeCommand);
        return cmd.getResult();
    }
}
