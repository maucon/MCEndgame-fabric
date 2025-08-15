package de.fuballer.mcendgame.main.component.item.custom.crystal.item.corruption

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.updateCustomAttributes
import net.minecraft.component.type.ItemEnchantmentsComponent
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.ItemStack

object CorruptionService {
    fun increaseEnchantLevel(stack: ItemStack): ItemStack {
        return stack.copy()
    }

    fun lowerEnchantLevel(stack: ItemStack): ItemStack {
        val builder = ItemEnchantmentsComponent.Builder(stack.enchantments)
        val enchantments = builder.enchantments
        if (enchantments.isEmpty()) return stack.copy()

        val chosenEnchantment = enchantments.random()
        val newLevel = builder.getLevel(chosenEnchantment) - 1
        builder.set(chosenEnchantment, newLevel)

        val result = stack.copy()
        EnchantmentHelper.set(result, builder.build())
        return result
    }

    fun addCurseEnchant(stack: ItemStack): ItemStack {
        return stack.copy()
    }

    fun enhanceAttribute(stack: ItemStack): ItemStack {
        val enhanceValue = CorruptionSettings.getAttributeChange()
        return changeAttribute(stack, enhanceValue)
    }

    fun diminishAttribute(stack: ItemStack): ItemStack {
        val diminishValue = -CorruptionSettings.getAttributeChange()
        return changeAttribute(stack, diminishValue)
    }

    fun changeAttribute(
        stack: ItemStack,
        value: Double,
    ): ItemStack {
        val oldAttributes = stack.getCustomAttributes()
        val possibleAttributes = oldAttributes.filter { it.hasNonZeroRangePercentRoll() }
        val chosenAttribute = possibleAttributes.random()

        val enhancedAttribute = chosenAttribute.getSingleRollEnhanced(value)

        val newAttributes = oldAttributes.toMutableList()
        val chosenAttributeIndex = oldAttributes.indexOf(chosenAttribute)
        newAttributes[chosenAttributeIndex] = enhancedAttribute

        val result = stack.copy()
        result.updateCustomAttributes(newAttributes)
        return result
    }
}