package de.fuballer.mcendgame.main.mixin.corruption;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.messaging.misc.CraftingRecipeCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerCorruptionUnmodifiableMixin {
    @Inject(method = "updateResult",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/inventory/CraftingResultInventory;setStack(ILnet/minecraft/item/ItemStack;)V"
            ),
            cancellable = true
    )
    private static void updateResult(
            ScreenHandler handler,
            ServerWorld world,
            PlayerEntity player,
            RecipeInputInventory craftingInventory,
            CraftingResultInventory resultInventory,
            @Nullable RecipeEntry<CraftingRecipe> recipe,
            CallbackInfo ci,
            @Local ServerPlayerEntity serverPlayerEntity,
            @Local ItemStack originalResult
    ) {
        var recipeCommand = new CraftingRecipeCommand(craftingInventory, originalResult);
        var cmd = CommandGateway.INSTANCE.apply(recipeCommand);

        var result = cmd.getResult();
        if (ItemStack.areEqual(cmd.getResult(), originalResult)) return;

        resultInventory.setStack(0, result);
        handler.setReceivedStack(0, result);
        serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 0, result));
        ci.cancel();
    }
}
