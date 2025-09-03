package de.fuballer.mcendgame.main.component.corruption

import net.minecraft.item.ItemStack

enum class CorruptionOutcome(
    val canApply: (ItemStack) -> Boolean,
    val apply: (ItemStack) -> ItemStack,
) {
    NOTHING({ true }, ItemStack::copy),
    DESTROY({ true }, { ItemStack.EMPTY }),
    INCREASE_ENCHANT_LEVEL(ItemStack::hasEnchantments, CorruptionService::increaseEnchantLevel),
    LOWER_ENCHANT_LEVEL(ItemStack::hasEnchantments, CorruptionService::lowerEnchantLevel),
    ADD_ENCHANT(CorruptionService::canAddEnchant, CorruptionService::addNonCurseEnchant),
    ADD_CURSE_ENCHANT(CorruptionService::canAddCurseEnchant, CorruptionService::addCurseEnchant),
    ENHANCE_ATTRIBUTE(CorruptionService::canChangeAttributeRoll, CorruptionService::enhanceAttribute),
    DIMINISH_ATTRIBUTE(CorruptionService::canChangeAttributeRoll, CorruptionService::diminishAttribute),
}