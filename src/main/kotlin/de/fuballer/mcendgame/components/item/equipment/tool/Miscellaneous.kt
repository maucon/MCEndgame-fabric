package de.fuballer.mcendgame.components.item.equipment.tool

import de.fuballer.mcendgame.components.item.equipment.Equipment
import de.fuballer.mcendgame.components.item.equipment.enchantment.EquipmentEnchantment
import de.fuballer.mcendgame.util.random.RandomOption
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.Items

@Suppress("unused")
enum class Miscellaneous(
    override val item: Item,
    override val slot: EquipmentSlot,
    override val rollableEnchants: List<RandomOption<EquipmentEnchantment>>,
) : Equipment {
    BOW(
        Items.BOW,
        EquipmentSlot.MAINHAND,
        listOf(
            RandomOption(10, EquipmentEnchantment.MENDING),
            RandomOption(20, EquipmentEnchantment.UNBREAKING_1),
            RandomOption(15, EquipmentEnchantment.UNBREAKING_2),
            RandomOption(10, EquipmentEnchantment.UNBREAKING_3),
            RandomOption(0, EquipmentEnchantment.CURSE_OF_VANISHING),
            RandomOption(25, EquipmentEnchantment.POWER_1),
            RandomOption(20, EquipmentEnchantment.POWER_2),
            RandomOption(15, EquipmentEnchantment.POWER_3),
            RandomOption(10, EquipmentEnchantment.POWER_4),
            RandomOption(5, EquipmentEnchantment.POWER_5),
            RandomOption(20, EquipmentEnchantment.PUNCH_1),
            RandomOption(10, EquipmentEnchantment.PUNCH_2),
            RandomOption(15, EquipmentEnchantment.FLAME),
            RandomOption(10, EquipmentEnchantment.INFINITY),
        )
    ),
    TRIDENT(
        Items.TRIDENT,
        EquipmentSlot.MAINHAND,
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
        EquipmentSlot.MAINHAND,
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
        EquipmentSlot.OFFHAND,
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
        EquipmentSlot.OFFHAND,
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
        EquipmentSlot.MAINHAND,
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
        EquipmentSlot.OFFHAND,
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
        EquipmentSlot.OFFHAND,
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
        EquipmentSlot.OFFHAND,
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
        EquipmentSlot.CHEST,
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
        EquipmentSlot.OFFHAND,
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