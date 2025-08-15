package de.fuballer.mcendgame.main.component.corruption

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getCustomAttributes
import net.minecraft.item.ItemStack

enum class CorruptionOutcome(
    val canApply: (ItemStack) -> Boolean,
    val apply: (ItemStack) -> ItemStack,
) {
    NOTHING({ true }, { it.copy() }),
    DESTROY({ true }, { ItemStack.EMPTY }),
    INCREASE_ENCHANT_LEVEL({ stack -> stack.hasEnchantments() }, { CorruptionService.increaseEnchantLevel(it) }),
    LOWER_ENCHANT_LEVEL({ stack -> stack.hasEnchantments() }, { CorruptionService.lowerEnchantLevel(it) }),
    ADD_ENCHANT({ CorruptionService.canAddEnchant(it) }, { CorruptionService.addNonCurseEnchant(it) }),
    ADD_CURSE_ENCHANT({ CorruptionService.canAddCurseEnchant(it) }, { CorruptionService.addCurseEnchant(it) }),
    ENHANCE_ATTRIBUTE({ stack -> stack.getCustomAttributes().any { it.hasNonZeroRangePercentRoll() } }, { CorruptionService.enhanceAttribute(it) }),
    DIMINISH_ATTRIBUTE({ stack -> stack.getCustomAttributes().any { it.hasNonZeroRangePercentRoll() } }, { CorruptionService.diminishAttribute(it) }),
}