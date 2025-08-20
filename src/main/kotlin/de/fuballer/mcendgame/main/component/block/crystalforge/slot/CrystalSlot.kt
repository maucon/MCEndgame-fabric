package de.fuballer.mcendgame.main.component.block.crystalforge.slot

import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot

class CrystalSlot(
    inventory: Inventory,
    index: Int,
    x: Int,
    y: Int,
) : Slot(inventory, index, x, y) {
    override fun canInsert(stack: ItemStack) = stack.item is CrystalItem
}