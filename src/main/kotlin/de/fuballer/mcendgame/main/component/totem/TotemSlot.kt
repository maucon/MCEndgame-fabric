package de.fuballer.mcendgame.main.component.totem

import de.fuballer.mcendgame.main.component.item.custom.totem.TotemItem
import de.fuballer.mcendgame.main.component.item.custom.totem.TotemType
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.util.Identifier

private val BACKGROUND_SPRITE: Identifier = IdentifierUtil.default("container/slot/totem")

class TotemSlot(
    inventory: Inventory,
    index: Int,
    x: Int,
    y: Int,
    val type: TotemType,
) : Slot(inventory, index, x, y) {
    override fun canInsert(stack: ItemStack) = (stack.item as? TotemItem)?.type == type

    override fun getBackgroundSprite() = BACKGROUND_SPRITE
}