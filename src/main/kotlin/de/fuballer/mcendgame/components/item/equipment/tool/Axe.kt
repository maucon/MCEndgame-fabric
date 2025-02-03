package de.fuballer.mcendgame.components.item.equipment.tool

import de.fuballer.mcendgame.components.item.equipment.Equipment
import de.fuballer.mcendgame.components.item.equipment.enchantment.EquipmentEnchantment
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.Items

enum class Axe(
    override val item: Item,
) : Equipment {
    WOODEN(
        Items.WOODEN_AXE,
    ),
    GOLDEN(
        Items.GOLDEN_AXE,
    ),
    STONE(
        Items.STONE_AXE,
    ),
    IRON(
        Items.IRON_AXE,
    ),
    DIAMOND(
        Items.DIAMOND_AXE,
    ),
    NETHERITE(
        Items.NETHERITE_AXE,
    );

    override val slot = EquipmentSlot.MAINHAND

    override val rollableEnchants = listOf(
        RandomOption(10, EquipmentEnchantment.MENDING),
        RandomOption(20, EquipmentEnchantment.UNBREAKING_1),
        RandomOption(15, EquipmentEnchantment.UNBREAKING_2),
        RandomOption(10, EquipmentEnchantment.UNBREAKING_3),
        RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
        RandomOption(25, EquipmentEnchantment.EFFICIENCY_1),
        RandomOption(20, EquipmentEnchantment.EFFICIENCY_2),
        RandomOption(15, EquipmentEnchantment.EFFICIENCY_3),
        RandomOption(10, EquipmentEnchantment.EFFICIENCY_4),
        RandomOption(5, EquipmentEnchantment.EFFICIENCY_5),
        RandomOption(10, EquipmentEnchantment.FORTUNE_1),
        RandomOption(6, EquipmentEnchantment.FORTUNE_2),
        RandomOption(3, EquipmentEnchantment.FORTUNE_3),
        RandomOption(15, EquipmentEnchantment.SILK_TOUCH),
        RandomOption(25, EquipmentEnchantment.SHARPNESS_1),
        RandomOption(20, EquipmentEnchantment.SHARPNESS_2),
        RandomOption(15, EquipmentEnchantment.SHARPNESS_3),
        RandomOption(10, EquipmentEnchantment.SHARPNESS_4),
        RandomOption(5, EquipmentEnchantment.SHARPNESS_5),
        RandomOption(15, EquipmentEnchantment.SMITE_1),
        RandomOption(12, EquipmentEnchantment.SMITE_2),
        RandomOption(9, EquipmentEnchantment.SMITE_3),
        RandomOption(6, EquipmentEnchantment.SMITE_4),
        RandomOption(3, EquipmentEnchantment.SMITE_5),
        RandomOption(15, EquipmentEnchantment.BANE_OF_ARTHROPODS_1),
        RandomOption(12, EquipmentEnchantment.BANE_OF_ARTHROPODS_2),
        RandomOption(9, EquipmentEnchantment.BANE_OF_ARTHROPODS_3),
        RandomOption(6, EquipmentEnchantment.BANE_OF_ARTHROPODS_4),
        RandomOption(3, EquipmentEnchantment.BANE_OF_ARTHROPODS_5),
        RandomOption(15, EquipmentEnchantment.FIRE_ASPECT_1),
        RandomOption(7, EquipmentEnchantment.FIRE_ASPECT_2),
    )
}