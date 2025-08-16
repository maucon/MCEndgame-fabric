package de.fuballer.mcendgame.main.mixin.corruption;

import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ForgingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ForgingScreenHandler.class)
public interface ForgingScreenHandlerOutputAccessorMixin {
    @Accessor("output")
    CraftingResultInventory getOutput();

    @Accessor("input")
    Inventory getInput();
}
