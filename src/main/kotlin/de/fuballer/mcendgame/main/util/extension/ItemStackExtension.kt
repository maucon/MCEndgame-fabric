package de.fuballer.mcendgame.main.util.extension

import de.fuballer.mcendgame.main.component.item.equipment.armor.Boots
import de.fuballer.mcendgame.main.component.item.equipment.armor.Chestplate
import de.fuballer.mcendgame.main.component.item.equipment.armor.Helmet
import de.fuballer.mcendgame.main.component.item.equipment.armor.Leggings
import de.fuballer.mcendgame.main.component.item.equipment.tool.*
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.ItemStack

object ItemStackExtension {
    fun ItemStack.isSameIgnoringDurability(other: ItemStack): Boolean {
        if (!isOf(other.item)) return false
        val ownComponents = components.filter { it.type != DataComponentTypes.DAMAGE }.associateBy { it.type }
        val otherComponents = other.components.filter { it.type != DataComponentTypes.DAMAGE }.associateBy { it.type }
        return ownComponents == otherComponents
    }

    fun ItemStack.isForgeable(): Boolean {
        if (Boots.entries.any { it.item == item }) return true
        if (Leggings.entries.any { it.item == item }) return true
        if (Chestplate.entries.any { it.item == item }) return true
        if (Helmet.entries.any { it.item == item }) return true
        if (Sword.entries.any { it.item == item }) return true
        if (Pickaxe.entries.any { it.item == item }) return true
        if (Axe.entries.any { it.item == item }) return true
        if (Shovel.entries.any { it.item == item }) return true
        if (Hoe.entries.any { it.item == item }) return true
        if (Bow.entries.any { it.item == item }) return true
        if (Miscellaneous.entries.any { it.item == item }) return true
        return false
    }
}