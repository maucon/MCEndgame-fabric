package de.fuballer.mcendgame.main.component.totem

import de.fuballer.mcendgame.main.component.item.custom.totem.TotemItem
import de.fuballer.mcendgame.main.component.item.custom.totem.TotemType
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot

class TotemSlot(
    inventory: Inventory,
    index: Int,
    x: Int,
    y: Int,
    val type: TotemType,
) : Slot(inventory, index, x, y) {
    override fun canInsert(stack: ItemStack) = (stack.item as? TotemItem)?.type == type
}