package de.fuballer.mcendgame.components.item.equipment.tool

import de.fuballer.mcendgame.components.item.equipment.Equipment
import de.fuballer.mcendgame.components.item.equipment.enchantment.EquipmentEnchantment
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.Items

enum class Sword(
    override val item: Item,
) : Equipment {
    WOODEN(
        Items.WOODEN_SWORD,
    ),
    GOLDEN(
        Items.GOLDEN_SWORD,
    ),
    STONE(
        Items.STONE_SWORD,
    ),
    IRON(
        Items.IRON_SWORD,
    ),
    DIAMOND(
        Items.DIAMOND_SWORD,
    ),
    NETHERITE(
        Items.NETHERITE_SWORD,
    );

    override val slot = EquipmentSlot.MAINHAND

    override val rollableEnchants = listOf(
        RandomOption(10, EquipmentEnchantment.MENDING),
        RandomOption(20, EquipmentEnchantment.UNBREAKING_1),
        RandomOption(15, EquipmentEnchantment.UNBREAKING_2),
        RandomOption(10, EquipmentEnchantment.UNBREAKING_3),
        RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
        RandomOption(15, EquipmentEnchantment.BANE_OF_ARTHROPODS_1),
        RandomOption(12, EquipmentEnchantment.BANE_OF_ARTHROPODS_2),
        RandomOption(9, EquipmentEnchantment.BANE_OF_ARTHROPODS_3),
        RandomOption(6, EquipmentEnchantment.BANE_OF_ARTHROPODS_4),
        RandomOption(3, EquipmentEnchantment.BANE_OF_ARTHROPODS_5),
        RandomOption(10, EquipmentEnchantment.FIRE_ASPECT_1),
        RandomOption(5, EquipmentEnchantment.FIRE_ASPECT_2),
        RandomOption(15, EquipmentEnchantment.LOOTING_1),
        RandomOption(10, EquipmentEnchantment.LOOTING_2),
        RandomOption(5, EquipmentEnchantment.LOOTING_3),
        RandomOption(10, EquipmentEnchantment.KNOCKBACK_1),
        RandomOption(5, EquipmentEnchantment.KNOCKBACK_2),
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
        RandomOption(15, EquipmentEnchantment.SWEEPING_EDGE_1),
        RandomOption(10, EquipmentEnchantment.SWEEPING_EDGE_2),
        RandomOption(5, EquipmentEnchantment.SWEEPING_EDGE_3),
    )
}