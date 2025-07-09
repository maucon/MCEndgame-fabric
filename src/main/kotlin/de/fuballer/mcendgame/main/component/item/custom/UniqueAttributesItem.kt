package de.fuballer.mcendgame.main.component.item.custom

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.setCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import kotlin.random.Random

abstract class UniqueAttributesItem(
    settings: Settings,
) : Item(settings) {
    abstract fun getCustomAttributes(): List<RollableCustomAttribute>

    abstract fun getAttributeModifierSlot(): AttributeModifierSlot

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
                    percentageRolls.add(lastRoll ?: Random.Default.nextDouble())
                }
            }

            attribute.roll(percentageRolls)
        }
    }

    fun getRolledStack(
        maxRoll: Boolean = false,
    ): ItemStack {
        return getRolledStack(if (maxRoll) listOf(1.0) else listOf())
    }

    fun getRolledStack(
        rolls: List<Double>,
    ): ItemStack {
        val stack = ItemStack(this)

        val attributes = getRolledAttributes(rolls)
        stack.setCustomAttributes(attributes, getAttributeModifierSlot())

        return stack
    }

    override fun getDefaultStack() = getRolledStack(true)
}