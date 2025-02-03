package de.fuballer.mcendgame.components.item.equipment.armor

import de.fuballer.mcendgame.components.item.equipment.Equipment
import de.fuballer.mcendgame.components.item.equipment.enchantment.EquipmentEnchantment
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.Items

enum class Boots(
    override val item: Item,
) : Equipment {
    LEATHER(
        Items.LEATHER_BOOTS,
    ),
    GOLDEN(
        Items.GOLDEN_BOOTS,
    ),
    CHAINMAIL(
        Items.CHAINMAIL_BOOTS,
    ),
    IRON(
        Items.IRON_BOOTS,
    ),
    DIAMOND(
        Items.DIAMOND_BOOTS,
    ),
    NETHERITE(
        Items.NETHERITE_BOOTS,
    );

    override val slot = EquipmentSlot.FEET

    override val rollableEnchants = listOf(
        RandomOption(20, EquipmentEnchantment.MENDING),
        RandomOption(35, EquipmentEnchantment.UNBREAKING_1),
        RandomOption(22, EquipmentEnchantment.UNBREAKING_2),
        RandomOption(11, EquipmentEnchantment.UNBREAKING_3),
        RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
        RandomOption(20, EquipmentEnchantment.BLAST_PROTECTION_1),
        RandomOption(15, EquipmentEnchantment.BLAST_PROTECTION_2),
        RandomOption(10, EquipmentEnchantment.BLAST_PROTECTION_3),
        RandomOption(5, EquipmentEnchantment.BLAST_PROTECTION_4),
        RandomOption(0, EquipmentEnchantment.CURSE_OF_BINDING),
        RandomOption(20, EquipmentEnchantment.FIRE_PROTECTION_1),
        RandomOption(15, EquipmentEnchantment.FIRE_PROTECTION_2),
        RandomOption(10, EquipmentEnchantment.FIRE_PROTECTION_3),
        RandomOption(5, EquipmentEnchantment.FIRE_PROTECTION_4),
        RandomOption(20, EquipmentEnchantment.PROJECTILE_PROTECTION_1),
        RandomOption(15, EquipmentEnchantment.PROJECTILE_PROTECTION_2),
        RandomOption(10, EquipmentEnchantment.PROJECTILE_PROTECTION_3),
        RandomOption(5, EquipmentEnchantment.PROJECTILE_PROTECTION_4),
        RandomOption(40, EquipmentEnchantment.PROTECTION_1),
        RandomOption(30, EquipmentEnchantment.PROTECTION_2),
        RandomOption(20, EquipmentEnchantment.PROTECTION_3),
        RandomOption(10, EquipmentEnchantment.PROTECTION_4),
        RandomOption(3, EquipmentEnchantment.THORNS_1),
        RandomOption(2, EquipmentEnchantment.THORNS_2),
        RandomOption(1, EquipmentEnchantment.THORNS_3),
        RandomOption(25, EquipmentEnchantment.DEPTH_STRIDER_1),
        RandomOption(15, EquipmentEnchantment.DEPTH_STRIDER_2),
        RandomOption(5, EquipmentEnchantment.DEPTH_STRIDER_3),
        RandomOption(20, EquipmentEnchantment.FEATHER_FALLING_1),
        RandomOption(15, EquipmentEnchantment.FEATHER_FALLING_2),
        RandomOption(10, EquipmentEnchantment.FEATHER_FALLING_3),
        RandomOption(5, EquipmentEnchantment.FEATHER_FALLING_4),
        RandomOption(20, EquipmentEnchantment.FROST_WALKER_1),
        RandomOption(10, EquipmentEnchantment.FROST_WALKER_2),
        RandomOption(25, EquipmentEnchantment.SOUL_SPEED_1),
        RandomOption(15, EquipmentEnchantment.SOUL_SPEED_2),
        RandomOption(5, EquipmentEnchantment.SOUL_SPEED_3),
    )
}