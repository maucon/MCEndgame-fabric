package de.fuballer.mcendgame.components.item.equipment.armor

import de.fuballer.mcendgame.components.item.equipment.Equipment
import de.fuballer.mcendgame.components.item.equipment.enchantment.EquipmentEnchantment
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.Items

enum class Helmet(
    override val item: Item,
) : Equipment {
    LEATHER(
        Items.LEATHER_HELMET,
    ),
    GOLDEN(
        Items.GOLDEN_HELMET,
    ),
    CHAINMAIL(
        Items.CHAINMAIL_HELMET,
    ),
    IRON(
        Items.IRON_HELMET,
    ),
    TURTLE(
        Items.TURTLE_HELMET,
    ),
    DIAMOND(
        Items.DIAMOND_HELMET,
    ),
    NETHERITE(
        Items.NETHERITE_HELMET,
    );

    override val slot = EquipmentSlot.HEAD

    override val rollableEnchants = listOf(
        RandomOption(10, EquipmentEnchantment.MENDING),
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
        RandomOption(35, EquipmentEnchantment.AQUA_AFFINITY_1),
        RandomOption(30, EquipmentEnchantment.RESPIRATION_1),
        RandomOption(20, EquipmentEnchantment.RESPIRATION_2),
        RandomOption(10, EquipmentEnchantment.RESPIRATION_3),
    )
}