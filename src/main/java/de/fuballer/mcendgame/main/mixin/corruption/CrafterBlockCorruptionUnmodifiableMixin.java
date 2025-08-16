package de.fuballer.mcendgame.main.mixin.corruption;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.messaging.misc.CraftingRecipeCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.block.CrafterBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.CraftingRecipeInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CrafterBlock.class)
public class CrafterBlockCorruptionUnmodifiableMixin {
    @ModifyVariable(
            method = "craft",
            at = @At(value = "STORE"),
            ordinal = 0
    )
    ItemStack on(
            ItemStack originalStack,
            @Local CraftingRecipeInput recipeInput
    ) {
        var recipeCommand = new CraftingRecipeCommand(recipeInput, originalStack);
        var cmd = CommandGateway.INSTANCE.apply(recipeCommand);
        return cmd.getResult();
    }
}