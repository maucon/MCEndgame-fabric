package de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.enchantment

object EnchantmentSettings {
    private const val ENCHANT_TRIES_PER_TIER = 0.5
    fun calculateEnchantTries(mapTier: Int) = 1 + (mapTier * ENCHANT_TRIES_PER_TIER).toInt()
}