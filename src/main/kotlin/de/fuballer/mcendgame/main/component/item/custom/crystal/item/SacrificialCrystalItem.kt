package de.fuballer.mcendgame.main.component.item.custom.crystal.item

import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeSettings
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.setCustomAttributes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItemInterface
import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import kotlin.math.max

private const val ENHANCE_BASE_PERCENTAGE = 0.1
private const val SACRIFICE_TO_ENHANCE_RATIO = 0.5

class SacrificialCrystalItem(
    settings: Settings,
) : CrystalItem(settings) {
    override val description: MutableText = Text.translatable(DESCRIPTION_BASE_KEY + "sacrificial")

    override fun canForge(stack: ItemStack): MutableText? {
        val cannotForgeReason = super.canForge(stack)
        if (cannotForgeReason != null) return cannotForgeReason

        if (stack.item is UniqueAttributesItemInterface) return CrystalForgeSettings.getForgeErrorText("cannot_forge_unique")

        val attributes = stack.getCustomAttributes()
        if (attributes.size < 2) return CrystalForgeSettings.getForgeErrorText("not_enough_attributes")
        if (attributes.none { it.hasNonZeroRange() }) return CrystalForgeSettings.getForgeErrorText("no_attribute_with_range")

        return null
    }

    override fun forge(stack: ItemStack): ItemStack {
        val newStack = stack.copy()

        val oldAttributes = stack.getCustomAttributes()
        if (oldAttributes.size < 2) return newStack
        val slot = oldAttributes[0].slot

        val attributesWithRange = oldAttributes.filter { it.hasNonZeroRange() }
        val toEnhance = attributesWithRange.randomOrNull() ?: return newStack

        val remainingAttributes = oldAttributes.filter { it != toEnhance }
        val sacrifice = remainingAttributes.random()
        val sacrificePercentage = sacrifice.getAffinityBasedRollPercentage()

        val enhancePercentage = ENHANCE_BASE_PERCENTAGE + max(sacrificePercentage * SACRIFICE_TO_ENHANCE_RATIO, 0.0)
        val enhanced = toEnhance.getEnhanced(enhancePercentage)

        val newAttributes = remainingAttributes.filter { it != sacrifice }.toMutableList()
        newAttributes.add(enhanced)
        newStack.setCustomAttributes(newAttributes, slot)

        return newStack
    }
}