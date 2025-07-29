package de.fuballer.mcendgame.main.component.dungeon.enemy.equipment

import de.fuballer.mcendgame.main.component.item.equipment.Equipment
import de.fuballer.mcendgame.main.component.item.equipment.armor.Boots
import de.fuballer.mcendgame.main.component.item.equipment.armor.Chestplate
import de.fuballer.mcendgame.main.component.item.equipment.armor.Helmet
import de.fuballer.mcendgame.main.component.item.equipment.armor.Leggings
import de.fuballer.mcendgame.main.component.item.equipment.tool.*
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.fuballer.mcendgame.main.util.random.SortableRandomOption
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.equipment.trim.ArmorTrimMaterial
import net.minecraft.item.equipment.trim.ArmorTrimMaterials
import net.minecraft.item.equipment.trim.ArmorTrimPattern
import net.minecraft.item.equipment.trim.ArmorTrimPatterns
import net.minecraft.registry.RegistryKey

object EquipmentGenerationSettings {
    private const val EQUIPMENT_ROLL_TRIES_PER_TIER = 0.25
    fun calculateEquipmentRollTries(mapTier: Int) = 1 + (mapTier * EQUIPMENT_ROLL_TRIES_PER_TIER).toInt()

    const val UNIQUE_EQUIPMENT_PROBABILITY = 0.01

    val UNIQUE_EQUIPMENT = mapOf<EquipmentSlot, List<Equipment>>(
        EquipmentSlot.MAINHAND to listOf(
            Sword.TWINFIRE,
            Sword.BLOODHARVEST,
        ),
        EquipmentSlot.OFFHAND to listOf(
            Sword.TWINFIRE,
            Sword.BLOODHARVEST,
        ),
        EquipmentSlot.HEAD to listOf(
            Helmet.ICEBORNE,
            Helmet.EMBERCHANT,
            Helmet.DRUIDS_HELMET,
            Helmet.WITHER_ROSE_HELMET,
            Helmet.SUEDE_HELMET,
        ),
        EquipmentSlot.CHEST to listOf(
            Chestplate.BOUND_ABYSS,
            Chestplate.DRUIDS_CHESTPLATE,
            Chestplate.WITHER_ROSE_CHESTPLATE,
            Chestplate.SUEDE_CHESTPLATE,
        ),
        EquipmentSlot.LEGS to listOf(
            Leggings.LAMIAS_GIFT,
            Leggings.DRUIDS_LEGGINGS,
            Leggings.WITHER_ROSE_LEGGINGS,
        ),
        EquipmentSlot.FEET to listOf(
            Boots.DRUIDS_BOOTS,
            Boots.WITHER_ROSE_BOOTS,
            Boots.SUEDE_BOOTS,
        ),
    )

    val UNIQUE_RANGED_EQUIPMENT = listOf(
        Bow.WINDSTRING,
        Bow.HAILSTORM,
        Bow.DUSK_PIERCER,
    )

    val HELMETS = listOf(
        SortableRandomOption(500, 0, null),
        SortableRandomOption(500, 1, Helmet.LEATHER),
        SortableRandomOption(500, 2, Helmet.GOLDEN),
        SortableRandomOption(500, 3, Helmet.CHAINMAIL),
        SortableRandomOption(500, 4, Helmet.IRON),
        SortableRandomOption(100, 5, Helmet.TURTLE),
        SortableRandomOption(800, 6, Helmet.DIAMOND),
        SortableRandomOption(500, 7, Helmet.NETHERITE),
    )
    val CHESTPLATES = listOf(
        SortableRandomOption(500, 0, null),
        SortableRandomOption(500, 1, Chestplate.LEATHER),
        SortableRandomOption(500, 2, Chestplate.GOLDEN),
        SortableRandomOption(500, 3, Chestplate.CHAINMAIL),
        SortableRandomOption(500, 4, Chestplate.IRON),
        SortableRandomOption(800, 6, Chestplate.DIAMOND),
        SortableRandomOption(500, 7, Chestplate.NETHERITE)
    )
    val LEGGINGS = listOf(
        SortableRandomOption(500, 0, null),
        SortableRandomOption(500, 1, Leggings.LEATHER),
        SortableRandomOption(500, 2, Leggings.GOLDEN),
        SortableRandomOption(500, 3, Leggings.CHAINMAIL),
        SortableRandomOption(500, 4, Leggings.IRON),
        SortableRandomOption(800, 6, Leggings.DIAMOND),
        SortableRandomOption(500, 7, Leggings.NETHERITE)
    )
    val BOOTS = listOf(
        SortableRandomOption(500, 0, null),
        SortableRandomOption(500, 1, Boots.LEATHER),
        SortableRandomOption(500, 2, Boots.GOLDEN),
        SortableRandomOption(500, 3, Boots.CHAINMAIL),
        SortableRandomOption(500, 4, Boots.IRON),
        SortableRandomOption(800, 6, Boots.DIAMOND),
        SortableRandomOption(500, 7, Boots.NETHERITE)
    )

    val ARMORSLOT_EQUIPMENT_MAP = mapOf<EquipmentSlot, List<SortableRandomOption<out Equipment?>>>(
        EquipmentSlot.HEAD to HELMETS,
        EquipmentSlot.CHEST to CHESTPLATES,
        EquipmentSlot.LEGS to LEGGINGS,
        EquipmentSlot.FEET to BOOTS,
    )

    private val SWORDS = listOf(
        SortableRandomOption(500, 1, Sword.WOODEN),
        SortableRandomOption(500, 2, Sword.GOLDEN),
        SortableRandomOption(500, 3, Sword.STONE),
        SortableRandomOption(500, 4, Sword.IRON),
        SortableRandomOption(800, 5, Sword.DIAMOND),
        SortableRandomOption(500, 6, Sword.NETHERITE),
    )
    private val AXES = listOf(
        SortableRandomOption(500, 1, Axe.WOODEN),
        SortableRandomOption(500, 2, Axe.GOLDEN),
        SortableRandomOption(500, 3, Axe.STONE),
        SortableRandomOption(500, 4, Axe.IRON),
        SortableRandomOption(800, 5, Axe.DIAMOND),
        SortableRandomOption(500, 6, Axe.NETHERITE)
    )
    private val PICKAXES = listOf(
        SortableRandomOption(500, 1, Pickaxe.WOODEN),
        SortableRandomOption(500, 2, Pickaxe.GOLDEN),
        SortableRandomOption(500, 3, Pickaxe.STONE),
        SortableRandomOption(500, 4, Pickaxe.IRON),
        SortableRandomOption(800, 5, Pickaxe.DIAMOND),
        SortableRandomOption(500, 6, Pickaxe.NETHERITE)
    )
    private val SHOVELS = listOf(
        SortableRandomOption(500, 1, Shovel.WOODEN),
        SortableRandomOption(500, 2, Shovel.GOLDEN),
        SortableRandomOption(500, 3, Shovel.STONE),
        SortableRandomOption(500, 4, Shovel.IRON),
        SortableRandomOption(800, 5, Shovel.DIAMOND),
        SortableRandomOption(500, 6, Shovel.NETHERITE),
    )
    private val HOES = listOf(
        SortableRandomOption(500, 1, Hoe.WOODEN),
        SortableRandomOption(500, 2, Hoe.GOLDEN),
        SortableRandomOption(500, 3, Hoe.STONE),
        SortableRandomOption(500, 4, Hoe.IRON),
        SortableRandomOption(800, 5, Hoe.DIAMOND),
        SortableRandomOption(500, 6, Hoe.NETHERITE),
    )
    private val BOWS = listOf(
        SortableRandomOption(100, 0, Bow.BOW)
    )
    private val SPECIAL_WEAPONS = listOf(
        SortableRandomOption(100, 0, Miscellaneous.TRIDENT),
        SortableRandomOption(50, 0, Miscellaneous.MACE)
    )
    const val OFFHAND_OTHER_OVER_MAINHAND_PROBABILITY = 0.25
    val OTHER_ITEMS = listOf(
        RandomOption(10, Miscellaneous.FISHING_ROD),
        RandomOption(50, Miscellaneous.SHIELD)
    )
    val MAINHAND_PROBABILITIES = listOf<RandomOption<List<SortableRandomOption<out Equipment>>?>>(
        RandomOption(10, null),
        RandomOption(10, SWORDS),
        RandomOption(10, AXES),
        RandomOption(10, PICKAXES),
        RandomOption(10, SHOVELS),
        RandomOption(10, HOES),
        RandomOption(1, SPECIAL_WEAPONS),
    )
    val RANGED_MAINHAND_PROBABILITIES = listOf<RandomOption<List<SortableRandomOption<out Equipment>>>>(
        RandomOption(100, BOWS),
    )

    val LOOT_GOBLIN_ARMOR_TRIM_MATERIALS = listOf<RandomOption<RegistryKey<ArmorTrimMaterial>>>(
        RandomOption(1, ArmorTrimMaterials.NETHERITE),
        RandomOption(3, ArmorTrimMaterials.DIAMOND),
        RandomOption(5, ArmorTrimMaterials.EMERALD),
        RandomOption(5, ArmorTrimMaterials.AMETHYST),
        RandomOption(5, ArmorTrimMaterials.QUARTZ),
        RandomOption(5, ArmorTrimMaterials.RESIN),
        RandomOption(8, ArmorTrimMaterials.GOLD),
        RandomOption(8, ArmorTrimMaterials.IRON),
        RandomOption(8, ArmorTrimMaterials.COPPER),
        RandomOption(8, ArmorTrimMaterials.REDSTONE),
        RandomOption(8, ArmorTrimMaterials.LAPIS),
    )

    val LOOT_GOBLIN_ARMOR_TRIM_PATTERNS = listOf<RandomOption<RegistryKey<ArmorTrimPattern>>>(
        RandomOption(1, ArmorTrimPatterns.SILENCE), //Ancient City chest 1.2%
        RandomOption(3, ArmorTrimPatterns.WARD),  //Ancient City chest 5%
        RandomOption(5, ArmorTrimPatterns.VEX), //Woodland Mansion chest 50%
        RandomOption(5, ArmorTrimPatterns.BOLT), //Vault 6.3%
        RandomOption(7, ArmorTrimPatterns.FLOW), //Ominous Vault 22.5%
        RandomOption(8, ArmorTrimPatterns.SNOUT), //Bastion Remnant chest 8.3%
        RandomOption(8, ArmorTrimPatterns.WILD), //Jungle Temple chest 33.3%
        RandomOption(10, ArmorTrimPatterns.EYE), //Stronghold altar chest 10%, library chest 100%
        RandomOption(15, ArmorTrimPatterns.SPIRE), //End city chest 6.7%
        RandomOption(15, ArmorTrimPatterns.TIDE), //Elder Guardian 20%
        RandomOption(15, ArmorTrimPatterns.RIB), //Nether Fortress chest 6.7%
        RandomOption(15, ArmorTrimPatterns.DUNE), //Desert Temple 14.3%
        RandomOption(15, ArmorTrimPatterns.WAYFINDER), //Suspicious gravel 8.3%
        RandomOption(15, ArmorTrimPatterns.SHAPER), //Suspicious gravel 8.3%
        RandomOption(15, ArmorTrimPatterns.RAISER), //Suspicious gravel 8.3%
        RandomOption(15, ArmorTrimPatterns.HOST), //Suspicious gravel 8.3%
        RandomOption(20, ArmorTrimPatterns.SENTRY), //Outpost chest 25%
        RandomOption(25, ArmorTrimPatterns.COAST), //Shipwreck chest 16.7%
    )
}