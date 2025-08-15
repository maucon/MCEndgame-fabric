package de.fuballer.mcendgame.main.util.extension

import de.fuballer.mcendgame.main.component.item.equipment.Equipment
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.ItemStack

object ItemStackExtension {
    fun ItemStack.isSameIgnoringDurability(other: ItemStack): Boolean {
        if (!isOf(other.item)) return false
        val ownComponents = components.filter { it.type != DataComponentTypes.DAMAGE }.associateBy { it.type }
        val otherComponents = other.components.filter { it.type != DataComponentTypes.DAMAGE }.associateBy { it.type }
        return ownComponents == otherComponents
    }

    fun ItemStack.isForgeable() = Equipment.fromItem(item) != null
}