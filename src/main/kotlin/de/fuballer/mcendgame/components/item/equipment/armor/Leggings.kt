package de.fuballer.mcendgame.components.item.equipment.armor

import de.fuballer.mcendgame.components.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.components.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.components.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.components.item.custom.armor.CustomArmorItems
import de.fuballer.mcendgame.components.item.equipment.Equipment
import de.fuballer.mcendgame.components.item.equipment.enchantment.EquipmentEnchantment
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.item.Item
import net.minecraft.item.Items

enum class Leggings(
    override val item: Item,
) : Equipment {
    LEATHER(
        Items.LEATHER_LEGGINGS,
    ),
    GOLDEN(
        Items.GOLDEN_LEGGINGS,
    ),
    CHAINMAIL(
        Items.CHAINMAIL_LEGGINGS,
    ),
    IRON(
        Items.IRON_LEGGINGS,
    ),
    DIAMOND(
        Items.DIAMOND_LEGGINGS,
    ),
    NETHERITE(
        Items.NETHERITE_LEGGINGS,
    ),
    DRUIDS_LEGGINGS(
        CustomArmorItems.DRUIDS_LEGGINGS,
    ),
    LAMIAS_GIFT(
        CustomArmorItems.LAMIAS_GIFT,
    );

    override val slot = AttributeModifierSlot.LEGS

    override val rollableCustomAttributes = listOf(
        RandomOption(5, RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 1, DoubleBounds(3.0, 4.0))),
        RandomOption(10, RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 2, DoubleBounds(2.0, 3.0))),
        RandomOption(20, RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 3, DoubleBounds(1.0, 2.0))),
        RandomOption(5, RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 1, DoubleBounds(3.0, 4.0))),
        RandomOption(10, RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 2, DoubleBounds(2.0, 3.0))),
        RandomOption(20, RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 3, DoubleBounds(1.0, 2.0))),
        RandomOption(5, RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 1, DoubleBounds(4.0, 5.0))),
        RandomOption(10, RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 2, DoubleBounds(2.5, 4.0))),
        RandomOption(20, RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 3, DoubleBounds(1.0, 2.0))),
        RandomOption(5, RollableCustomAttribute(CustomAttributeTypes.WARD, 1, DoubleBounds(3.0, 4.0))),
        RandomOption(10, RollableCustomAttribute(CustomAttributeTypes.WARD, 2, DoubleBounds(2.0, 3.0))),
        RandomOption(20, RollableCustomAttribute(CustomAttributeTypes.WARD, 3, DoubleBounds(1.0, 2.0))),
        RandomOption(5, RollableCustomAttribute(CustomAttributeTypes.DODGE, 1, DoubleBounds(0.15, 0.2))),
        RandomOption(10, RollableCustomAttribute(CustomAttributeTypes.DODGE, 2, DoubleBounds(0.1, 0.15))),
        RandomOption(20, RollableCustomAttribute(CustomAttributeTypes.DODGE, 3, DoubleBounds(0.05, 0.1))),
        RandomOption(5, RollableCustomAttribute(CustomAttributeTypes.LESS_DAMAGE_TAKEN, 1, DoubleBounds(0.11, 0.15))),
        RandomOption(10, RollableCustomAttribute(CustomAttributeTypes.LESS_DAMAGE_TAKEN, 2, DoubleBounds(0.07, 0.11))),
        RandomOption(20, RollableCustomAttribute(CustomAttributeTypes.LESS_DAMAGE_TAKEN, 3, DoubleBounds(0.03, 0.07))),
    )

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
        RandomOption(28, EquipmentEnchantment.SWIFT_SNEAK_1),
        RandomOption(18, EquipmentEnchantment.SWIFT_SNEAK_2),
        RandomOption(8, EquipmentEnchantment.SWIFT_SNEAK_3),
    )
}