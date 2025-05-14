package de.fuballer.mcendgame.main.component.item.custom.armor.interfaces

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.setCustomAttributes
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
        maxRoll: Boolean = false,
    ) = getCustomAttributes().map {
        if (maxRoll) it.roll(List(it.bounds.size) { 1.0 })
        else {
            val percentageRolls = mutableListOf<Double>()
            repeat(it.bounds.size) {
                percentageRolls.add(Random.nextDouble())
            }
            it.roll(percentageRolls)
        }
    }

    fun getRolledStack(
        maxRoll: Boolean = false,
    ): ItemStack {
        val stack = ItemStack(this)

        val attributes = getRolledAttributes(maxRoll)
        stack.setCustomAttributes(attributes, getAttributeModifierSlot())

        return stack
    }

    override fun getDefaultStack() = getRolledStack(true)
}