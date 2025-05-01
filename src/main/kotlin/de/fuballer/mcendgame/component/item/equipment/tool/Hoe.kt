package de.fuballer.mcendgame.component.item.equipment.tool

import de.fuballer.mcendgame.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.component.item.equipment.Equipment
import de.fuballer.mcendgame.component.item.equipment.enchantment.EquipmentEnchantment
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.item.Item
import net.minecraft.item.Items

enum class Hoe(
    override val item: Item,
) : Equipment {
    WOODEN(
        Items.WOODEN_HOE,
    ),
    GOLDEN(
        Items.GOLDEN_HOE,
    ),
    STONE(
        Items.STONE_HOE,
    ),
    IRON(
        Items.IRON_HOE,
    ),
    DIAMOND(
        Items.DIAMOND_HOE,
    ),
    NETHERITE(
        Items.NETHERITE_HOE,
    );

    override val slot = AttributeModifierSlot.MAINHAND

    override val rollableCustomAttributes: List<RandomOption<RollableCustomAttribute>>
        get() = listOf(
            RandomOption(5, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 1, DoubleBounds(2.0, 2.5))),
            RandomOption(10, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 2, DoubleBounds(1.2, 2.0))),
            RandomOption(20, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 3, DoubleBounds(0.4, 1.2))),
            RandomOption(5, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 1, DoubleBounds(0.25, 0.35))),
            RandomOption(10, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 2, DoubleBounds(0.15, 0.25))),
            RandomOption(20, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 3, DoubleBounds(0.05, 0.15))),
            RandomOption(5, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 1, DoubleBounds(0.15, 0.2))),
            RandomOption(10, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 2, DoubleBounds(0.1, 0.15))),
            RandomOption(20, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 3, DoubleBounds(0.05, 0.1))),
            RandomOption(5, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 1, DoubleBounds(2.0, 2.5))),
            RandomOption(10, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 2, DoubleBounds(1.2, 2.0))),
            RandomOption(20, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 3, DoubleBounds(0.4, 1.2))),
            RandomOption(5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 1, DoubleBounds(0.25, 0.35))),
            RandomOption(10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 2, DoubleBounds(0.15, 0.25))),
            RandomOption(20, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 3, DoubleBounds(0.05, 0.15))),
            RandomOption(5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 1, DoubleBounds(0.15, 0.2))),
            RandomOption(10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 2, DoubleBounds(0.1, 0.15))),
            RandomOption(20, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 3, DoubleBounds(0.05, 0.1))),
        )

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
    )
}