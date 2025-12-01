package de.fuballer.mcendgame.main.mixin.smithing;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Optional;

@Mixin(SmithingScreenHandler.class)
public class SmithingScreenHandlerUpgradeAttributesMixin {
    @Shadow
    @Final
    private World world;

    @Inject(
            method = "updateResult",
            at = @At(value = "INVOKE", target = "Ljava/util/Optional;ifPresentOrElse(Ljava/util/function/Consumer;Ljava/lang/Runnable;)V"),
            cancellable = true)
    void updateResult(
            CallbackInfo ci,
            @Local SmithingRecipeInput originalRecipeInput,
            @Local Optional<RecipeEntry<SmithingRecipe>> optional
    ) {
        if (optional.isEmpty()) return;
        var recipeEntry = optional.get();
        if (!(recipeEntry.value() instanceof SmithingTransformRecipe recipe)) return;

        var inputStack = originalRecipeInput.base();
        var attributes = CustomAttributesExtensions.INSTANCE.getCustomAttributes(inputStack);
        if (attributes.isEmpty()) return;

        var inputCopy = inputStack.copy();
        CustomAttributesExtensions.INSTANCE.updateCustomAttributes(inputCopy, new ArrayList<>());
        var slot = attributes.getFirst().getSlot();

        var template = originalRecipeInput.template();
        var recipeInput = new SmithingRecipeInput(template, inputCopy, originalRecipeInput.addition());

        var resultStack = recipe.craft(recipeInput, world.getRegistryManager());
        CustomAttributesExtensions.INSTANCE.setCustomAttributes(resultStack, attributes, slot);

        var accessor = (ForgingScreenHandlerAccessor) this;
        var output = accessor.getOutput();
        output.setLastRecipe(recipeEntry);
        output.setStack(0, resultStack);

        ci.cancel();
    }
}
