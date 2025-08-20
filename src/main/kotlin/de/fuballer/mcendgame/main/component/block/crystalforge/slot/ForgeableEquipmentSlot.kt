package de.fuballer.mcendgame.main.component.block.crystalforge.slot

import de.fuballer.mcendgame.main.util.extension.ItemStackExtension.isForgeable
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot

class ForgeableEquipmentSlot(
    inventory: Inventory,
    index: Int,
    x: Int,
    y: Int,
) : Slot(inventory, index, x, y) {
    override fun canInsert(stack: ItemStack) = stack.isForgeable()
}