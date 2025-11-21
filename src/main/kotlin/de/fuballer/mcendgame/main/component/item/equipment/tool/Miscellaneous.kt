package de.fuballer.mcendgame.main.component.item.equipment.tool

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.equipment.Equipment
import de.fuballer.mcendgame.main.component.item.equipment.enchantment.EquipmentEnchantment
import de.fuballer.mcendgame.main.util.random.LevelLockedRandomOption
import de.fuballer.mcendgame.main.util.random.RandomOption
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.item.Item
import net.minecraft.item.Items

@Suppress("unused")
enum class Miscellaneous(
    override val item: Item,
    override val slot: AttributeModifierSlot,
    override val rollableCustomAttributes: List<RandomOption<List<LevelLockedRandomOption<RollableCustomAttribute>>>>,
    override val rollableEnchants: List<RandomOption<EquipmentEnchantment>>,
) : Equipment {
    TRIDENT(
        Items.TRIDENT,
        AttributeModifierSlot.HAND,
        listOf(
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 3, DoubleBounds(0.6, 1.4))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 2, DoubleBounds(1.4, 2.2))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 1, DoubleBounds(2.2, 3.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 3, DoubleBounds(0.03, 0.06))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 2, DoubleBounds(0.06, 0.09))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 1, DoubleBounds(0.09, 0.12))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 3, DoubleBounds(0.05, 0.1))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 2, DoubleBounds(0.1, 0.15))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 1, DoubleBounds(0.15, 0.2))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 3, DoubleBounds(0.6, 1.4))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 2, DoubleBounds(1.4, 2.2))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 1, DoubleBounds(2.2, 3.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 3, DoubleBounds(0.05, 0.1))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 2, DoubleBounds(0.1, 0.15))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 1, DoubleBounds(0.15, 0.2))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 3, DoubleBounds(0.015, 0.03))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 2, DoubleBounds(0.03, 0.045))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 1, DoubleBounds(0.045, 0.06))),
                )
            ),
        ),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(10, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
            RandomOption(10, EquipmentEnchantment.CHANNELING),
            RandomOption(15, EquipmentEnchantment.LOYALTY_1),
            RandomOption(10, EquipmentEnchantment.LOYALTY_2),
            RandomOption(5, EquipmentEnchantment.LOYALTY_3),
            RandomOption(25, EquipmentEnchantment.IMPALING_1),
            RandomOption(20, EquipmentEnchantment.IMPALING_2),
            RandomOption(15, EquipmentEnchantment.IMPALING_3),
            RandomOption(10, EquipmentEnchantment.IMPALING_4),
            RandomOption(5, EquipmentEnchantment.IMPALING_5),
            RandomOption(15, EquipmentEnchantment.RIPTIDE_1),
            RandomOption(10, EquipmentEnchantment.RIPTIDE_2),
            RandomOption(5, EquipmentEnchantment.RIPTIDE_3),
        )
    ),
    MACE(
        Items.MACE,
        AttributeModifierSlot.HAND,
        listOf(
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 3, DoubleBounds(0.6, 1.4))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 2, DoubleBounds(1.4, 2.2))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 1, DoubleBounds(2.2, 3.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 3, DoubleBounds(0.03, 0.06))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 2, DoubleBounds(0.06, 0.09))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 1, DoubleBounds(0.09, 0.12))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 3, DoubleBounds(0.05, 0.1))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 2, DoubleBounds(0.1, 0.15))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 1, DoubleBounds(0.15, 0.2))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 3, DoubleBounds(0.6, 1.4))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 2, DoubleBounds(1.4, 2.2))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 1, DoubleBounds(2.2, 3.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 3, DoubleBounds(0.05, 0.1))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 2, DoubleBounds(0.1, 0.15))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 1, DoubleBounds(0.15, 0.2))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 3, DoubleBounds(0.015, 0.03))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 2, DoubleBounds(0.03, 0.045))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 1, DoubleBounds(0.045, 0.06))),
                )
            ),
        ),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(10, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
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
            RandomOption(10, EquipmentEnchantment.FIRE_ASPECT_1),
            RandomOption(5, EquipmentEnchantment.FIRE_ASPECT_2),
            RandomOption(25, EquipmentEnchantment.DENSITY_1),
            RandomOption(20, EquipmentEnchantment.DENSITY_2),
            RandomOption(15, EquipmentEnchantment.DENSITY_3),
            RandomOption(10, EquipmentEnchantment.DENSITY_4),
            RandomOption(5, EquipmentEnchantment.DENSITY_5),
            RandomOption(12, EquipmentEnchantment.BREACH_1),
            RandomOption(9, EquipmentEnchantment.BREACH_2),
            RandomOption(6, EquipmentEnchantment.BREACH_3),
            RandomOption(3, EquipmentEnchantment.BREACH_4),
            RandomOption(12, EquipmentEnchantment.WINDBURST_1),
            RandomOption(8, EquipmentEnchantment.WINDBURST_2),
            RandomOption(4, EquipmentEnchantment.WINDBURST_3),
        )
    ),
    FISHING_ROD(
        Items.FISHING_ROD,
        AttributeModifierSlot.OFFHAND,
        listOf(
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.LUCK, 3, DoubleBounds(0.5, 2.0))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.LUCK, 2, DoubleBounds(2.0, 3.5))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.LUCK, 1, DoubleBounds(3.5, 5.0))),
                )
            ),
        ),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(25, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
            RandomOption(20, EquipmentEnchantment.LUCK_OF_THE_SEA_1),
            RandomOption(15, EquipmentEnchantment.LUCK_OF_THE_SEA_2),
            RandomOption(10, EquipmentEnchantment.LUCK_OF_THE_SEA_3),
            RandomOption(20, EquipmentEnchantment.LURE_1),
            RandomOption(15, EquipmentEnchantment.LURE_2),
            RandomOption(10, EquipmentEnchantment.LURE_3),
        )
    ),
    SHIELD(
        Items.SHIELD,
        AttributeModifierSlot.OFFHAND,
        listOf(
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 3, DoubleBounds(0.01, 0.025))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 2, DoubleBounds(0.025, 0.04))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 1, DoubleBounds(0.04, 0.05))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 3, DoubleBounds(0.02, 0.07))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 2, DoubleBounds(0.07, 0.12))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 1, DoubleBounds(0.12, 0.17))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 3, DoubleBounds(0.6, 1.4))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 2, DoubleBounds(1.4, 2.2))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 1, DoubleBounds(2.2, 3.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 3, DoubleBounds(0.02, 0.07))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 2, DoubleBounds(0.07, 0.12))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 1, DoubleBounds(0.12, 0.17))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 3, DoubleBounds(0.025, 0.05))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 2, DoubleBounds(0.05, 0.075))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 1, DoubleBounds(0.075, 0.1))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 3, DoubleBounds(0.3, 0.7))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 2, DoubleBounds(0.7, 1.1))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 1, DoubleBounds(1.1, 1.5))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 3, DoubleBounds(0.5, 1.0))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 2, DoubleBounds(1.0, 1.5))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 1, DoubleBounds(1.5, 2.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 3, DoubleBounds(0.5, 1.0))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 2, DoubleBounds(1.0, 1.5))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 1, DoubleBounds(1.5, 2.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.WARD, 3, DoubleBounds(0.5, 1.0))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.WARD, 2, DoubleBounds(1.0, 1.5))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.WARD, 1, DoubleBounds(1.5, 2.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.DODGE, 3, DoubleBounds(0.01, 0.03))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.DODGE, 2, DoubleBounds(0.03, 0.05))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.DODGE, 1, DoubleBounds(0.05, 0.06))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, 3, DoubleBounds(-0.025, -0.01))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, 2, DoubleBounds(-0.04, -0.025))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, 1, DoubleBounds(-0.05, -0.04))),
                )
            ),
        ),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(25, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
        )
    ),
    CROSSBOW(
        Items.CROSSBOW,
        AttributeModifierSlot.HAND,
        listOf(
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 3, DoubleBounds(0.015, 0.03))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 2, DoubleBounds(0.03, 0.045))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 1, DoubleBounds(0.045, 0.06))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.INCREASED_PROJECTILE_DAMAGE, 3, DoubleBounds(0.05, 0.1))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_PROJECTILE_DAMAGE, 2, DoubleBounds(0.1, 0.15))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_PROJECTILE_DAMAGE, 1, DoubleBounds(0.15, 0.2))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 3, DoubleBounds(0.05, 0.1))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 2, DoubleBounds(0.1, 0.15))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 1, DoubleBounds(0.15, 0.2))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 3, DoubleBounds(1.0, 2.0))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 2, DoubleBounds(2.0, 3.0))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 1, DoubleBounds(3.0, 4.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 3, DoubleBounds(0.03, 0.06))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 2, DoubleBounds(0.06, 0.09))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_DAMAGE, 1, DoubleBounds(0.09, 0.12))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 3, DoubleBounds(1.0, 2.0))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 2, DoubleBounds(2.0, 3.0))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 1, DoubleBounds(3.0, 4.0))),
                )
            ),
        ),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(25, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
            RandomOption(15, EquipmentEnchantment.MULTISHOT),
            RandomOption(20, EquipmentEnchantment.PIERCING_1),
            RandomOption(15, EquipmentEnchantment.PIERCING_2),
            RandomOption(10, EquipmentEnchantment.PIERCING_3),
            RandomOption(5, EquipmentEnchantment.PIERCING_4),
            RandomOption(15, EquipmentEnchantment.QUICK_CHARGE_1),
            RandomOption(10, EquipmentEnchantment.QUICK_CHARGE_2),
            RandomOption(5, EquipmentEnchantment.QUICK_CHARGE_3),
        )
    ),
    FLINT_AND_STEEL(
        Items.FLINT_AND_STEEL,
        AttributeModifierSlot.OFFHAND,
        listOf(),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(25, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
        )
    ),
    CARROT_ON_A_STICK(
        Items.CARROT_ON_A_STICK,
        AttributeModifierSlot.OFFHAND,
        listOf(),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(25, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
        )
    ),
    WARPED_FUNGUS_ON_A_STICK(
        Items.WARPED_FUNGUS_ON_A_STICK,
        AttributeModifierSlot.OFFHAND,
        listOf(),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(25, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
        )
    ),
    ELYTRA(
        Items.ELYTRA,
        AttributeModifierSlot.CHEST,
        listOf(
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 3, DoubleBounds(0.3, 0.7))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 2, DoubleBounds(0.7, 1.1))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 1, DoubleBounds(1.1, 1.5))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 3, DoubleBounds(0.5, 1.0))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 2, DoubleBounds(1.0, 1.5))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 1, DoubleBounds(1.5, 2.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 3, DoubleBounds(0.5, 1.0))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 2, DoubleBounds(1.0, 1.5))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 1, DoubleBounds(1.5, 2.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.WARD, 3, DoubleBounds(0.5, 1.0))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.WARD, 2, DoubleBounds(1.0, 1.5))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.WARD, 1, DoubleBounds(1.5, 2.0))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.DODGE, 3, DoubleBounds(0.01, 0.03))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.DODGE, 2, DoubleBounds(0.03, 0.05))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.DODGE, 1, DoubleBounds(0.05, 0.06))),
                )
            ),
            RandomOption(
                weight = 1,
                listOf(
                    LevelLockedRandomOption(weight = 50, tier = 1, level = 0, RollableCustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, 3, DoubleBounds(-0.025, -0.01))),
                    LevelLockedRandomOption(weight = 10, tier = 2, level = 5, RollableCustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, 2, DoubleBounds(-0.04, -0.025))),
                    LevelLockedRandomOption(weight = 1, tier = 3, level = 10, RollableCustomAttribute(CustomAttributeTypes.MORE_DAMAGE_TAKEN, 1, DoubleBounds(-0.05, -0.04))),
                )
            ),
        ),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(25, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_BINDING),
        )
    ),
    SHEARS(
        Items.SHEARS,
        AttributeModifierSlot.OFFHAND,
        listOf(),
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(25, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
            RandomOption(25, EquipmentEnchantment.EFFICIENCY_1),
            RandomOption(20, EquipmentEnchantment.EFFICIENCY_2),
            RandomOption(15, EquipmentEnchantment.EFFICIENCY_3),
            RandomOption(10, EquipmentEnchantment.EFFICIENCY_4),
            RandomOption(5, EquipmentEnchantment.EFFICIENCY_5),
        )
    );
}