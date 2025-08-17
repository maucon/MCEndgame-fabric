package de.fuballer.mcendgame.main.mixin.corruption;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.messaging.misc.CanSmithCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.screen.SmithingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Optional;

@Mixin(SmithingScreenHandler.class)
public class SmithingScreenHandlerCorruptionUnmodifiableMixin {
    @ModifyVariable(
            method = "updateResult",
            at = @At(value = "STORE"),
            ordinal = 0
    )
    Optional<RecipeEntry<SmithingRecipe>> updateResult(
            Optional<RecipeEntry<SmithingRecipe>> original,
            @Local SmithingRecipeInput smithingRecipeInput
    ) {
        if (original.isEmpty()) return original;

        var canSmithCommand = new CanSmithCommand(smithingRecipeInput, true);
        var cmd = CommandGateway.INSTANCE.apply(canSmithCommand);
        if (!cmd.getCanSmith()) return Optional.empty();

        return original;
    }
}
