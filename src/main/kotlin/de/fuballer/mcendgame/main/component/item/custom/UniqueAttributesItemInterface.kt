package de.fuballer.mcendgame.main.component.item.custom

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.setCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Colors
import kotlin.random.Random

interface UniqueAttributesItemInterface {
    fun getNameColor(): Int = Colors.WHITE

    fun getCustomAttributes(): List<RollableCustomAttribute>

    fun getAttributeModifierSlot(): AttributeModifierSlot

    private fun getRolledAttributes(
        rolls: List<Double>,
    ): List<CustomAttribute> {
        val iterator = rolls.iterator()
        val lastRoll = rolls.lastOrNull()

        return getCustomAttributes().map { attribute ->
            val percentageRolls = mutableListOf<Double>()

            repeat(attribute.bounds.size) {
                if (iterator.hasNext()) {
                    percentageRolls.add(iterator.next())
                } else {
                    percentageRolls.add(lastRoll ?: Random.nextDouble())
                }
            }

            attribute.roll(percentageRolls, getAttributeModifierSlot())
        }
    }

    fun getRolledStack(
        item: Item,
        maxRoll: Boolean = false,
    ): ItemStack {
        return getRolledStack(item, if (maxRoll) listOf(1.0) else listOf())
    }

    fun getRolledStack(
        item: Item,
        rolls: List<Double>,
    ): ItemStack {
        val stack = ItemStack(item)

        val attributes = getRolledAttributes(rolls)
        stack.setCustomAttributes(attributes, getAttributeModifierSlot())

        return stack
    }
}