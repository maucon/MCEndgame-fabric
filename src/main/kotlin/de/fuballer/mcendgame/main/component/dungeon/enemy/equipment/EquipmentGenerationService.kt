package de.fuballer.mcendgame.main.component.dungeon.enemy.equipment

import de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.attributes.AttributeService
import de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.data.EquipmentTag
import de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.enchantment.EnchantmentService
import de.fuballer.mcendgame.main.component.entity.EntityTypeStats
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItemInterface
import de.fuballer.mcendgame.main.component.item.equipment.Equipment
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateEnemiesCommand
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.fuballer.mcendgame.main.util.random.RandomUtil
import de.fuballer.mcendgame.main.util.random.SortableRandomOption
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.equipment.trim.ArmorTrim
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.MinecraftServer
import kotlin.random.Random

@Injectable
class EquipmentGenerationService(
    private val enchantmentService: EnchantmentService,
    private val attributeService: AttributeService,
) {
    fun generate(
        entity: LivingEntity,
        type: EntityTypeStats,
        level: Int,
        server: MinecraftServer,
        isLootGoblin: Boolean,
        random: Random,
        generateEnemiesCommand: DungeonGenerateEnemiesCommand,
    ) {
        val uniqueEquipmentProbability = generateEnemiesCommand.uniqueEquipmentProbability
        val luckyAttributes = isLootGoblin && generateEnemiesCommand.lootGoblinLuckyAttributes

        if (type.canHaveWeapons) {
            createEquipment(level, EquipmentSlot.MAINHAND, server, random, uniqueEquipmentProbability, luckyAttributes, isRanged = type.isRanged)?.also {
                entity.equipStack(EquipmentSlot.MAINHAND, it)
            }
            createEquipment(level, EquipmentSlot.OFFHAND, server, random, uniqueEquipmentProbability, luckyAttributes)?.also {
                entity.equipStack(EquipmentSlot.OFFHAND, it)
            }
        }

        if (!type.canHaveArmor) return

        val armorTrim = if (isLootGoblin) getArmorTrim(server, random) else null

        createEquipment(level, EquipmentSlot.HEAD, server, random, uniqueEquipmentProbability, luckyAttributes, armorTrim = armorTrim)?.also {
            entity.equipStack(EquipmentSlot.HEAD, it)
        }
        createEquipment(level, EquipmentSlot.CHEST, server, random, uniqueEquipmentProbability, luckyAttributes, armorTrim = armorTrim)?.also {
            entity.equipStack(EquipmentSlot.CHEST, it)
        }
        createEquipment(level, EquipmentSlot.LEGS, server, random, uniqueEquipmentProbability, luckyAttributes, armorTrim = armorTrim)?.also {
            entity.equipStack(EquipmentSlot.LEGS, it)
        }
        createEquipment(level, EquipmentSlot.FEET, server, random, uniqueEquipmentProbability, luckyAttributes, armorTrim = armorTrim)?.also {
            entity.equipStack(EquipmentSlot.FEET, it)
        }
    }

    private fun createEquipment(
        level: Int,
        slot: EquipmentSlot,
        server: MinecraftServer,
        random: Random,
        uniqueEquipmentProbability: Double,
        luckyAttributes: Boolean,
        isRanged: Boolean = false,
        armorTrim: ArmorTrim? = null,
    ): ItemStack? {
        if (random.nextDouble() <= uniqueEquipmentProbability) {
            return createUniqueEquipment(level, slot, server, random, isRanged)
        }

        return when (slot) {
            EquipmentSlot.MAINHAND -> createMainHandItem(level, isRanged, server, random, luckyAttributes)
            EquipmentSlot.OFFHAND -> createOffHandItem(level, server, random, luckyAttributes)
            else -> createArmorEquipment(level, slot, server, random, luckyAttributes, armorTrim)
        }
    }

    private fun createUniqueEquipment(
        level: Int,
        slot: EquipmentSlot,
        server: MinecraftServer,
        random: Random,
        isRanged: Boolean,
    ): ItemStack? {
        val equipment = (if (isRanged && slot == EquipmentSlot.MAINHAND) EquipmentGenerationSettings.getRandomUniqueEquipment(slot, EquipmentTag.RANGED, true, random)
        else EquipmentGenerationSettings.getRandomUniqueEquipment(slot, random = random)) ?: return null

        val item = equipment.item
        val itemStack = if (item is UniqueAttributesItemInterface) item.getRolledStack(item) else ItemStack(item)

        enchantmentService.enchantItem(itemStack, equipment.rollableEnchants, level, server, random)

        return itemStack
    }

    private fun createMainHandItem(
        level: Int,
        isRanged: Boolean,
        server: MinecraftServer,
        random: Random,
        luckyAttributes: Boolean,
    ): ItemStack? {
        val options = if (isRanged) {
            RandomUtil.pick(EquipmentGenerationSettings.RANGED_MAINHAND_PROBABILITIES, random).option
        } else {
            RandomUtil.pick(EquipmentGenerationSettings.MAINHAND_PROBABILITIES, random).option
        } ?: return null

        return createEquipmentSortable(level, options, server, random, luckyAttributes)
    }

    private fun createOffHandItem(
        level: Int,
        server: MinecraftServer,
        random: Random,
        luckyAttributes: Boolean,
    ): ItemStack? {
        if (random.nextDouble() < EquipmentGenerationSettings.OFFHAND_OTHER_OVER_MAINHAND_PROBABILITY) {
            return createEquipment(level, EquipmentGenerationSettings.OTHER_ITEMS, server, random, luckyAttributes)
        }

        return createMainHandItem(level, false, server, random, luckyAttributes)
    }

    private fun createArmorEquipment(
        level: Int,
        slot: EquipmentSlot,
        server: MinecraftServer,
        random: Random,
        luckyAttributes: Boolean,
        armorTrim: ArmorTrim? = null,
    ): ItemStack? {
        val equipmentOptions = EquipmentGenerationSettings.ARMORSLOT_EQUIPMENT_MAP[slot] ?: return null
        val stack = createEquipmentSortable(level, equipmentOptions, server, random, luckyAttributes) ?: return null

        stack.set(DataComponentTypes.TRIM, armorTrim)
        return stack
    }

    private fun createEquipmentSortable(
        level: Int,
        equipmentOptions: List<SortableRandomOption<out Equipment?>>,
        server: MinecraftServer,
        random: Random,
        luckyAttributes: Boolean,
    ): ItemStack? {
        val rolls = EquipmentGenerationSettings.calculateEquipmentRollTries(level)
        val equipment = RandomUtil.pick(equipmentOptions, rolls, random).option ?: return null

        return createEquipment(level, equipment, server, random, luckyAttributes)
    }

    private fun createEquipment(
        level: Int,
        equipmentOptions: List<RandomOption<out Equipment?>>,
        server: MinecraftServer,
        random: Random,
        luckyAttributes: Boolean,
    ): ItemStack? {
        val equipment = RandomUtil.pick(equipmentOptions, random).option ?: return null

        return createEquipment(level, equipment, server, random, luckyAttributes)
    }

    private fun createEquipment(
        level: Int,
        equipment: Equipment,
        server: MinecraftServer,
        random: Random,
        luckyAttributes: Boolean,
    ): ItemStack {
        val item = equipment.item
        val itemStack = ItemStack(item)

        enchantmentService.enchantItem(itemStack, equipment.rollableEnchants, level, server, random)
        attributeService.applyAttributes(itemStack, equipment.rollableCustomAttributes, level, random, equipment.slot, luckyAttributes)

        return itemStack
    }

    private fun getArmorTrim(
        server: MinecraftServer,
        random: Random
    ): ArmorTrim {
        val materialRegistry = server.registryManager.getOrThrow(RegistryKeys.TRIM_MATERIAL)
        val patternRegistry = server.registryManager.getOrThrow(RegistryKeys.TRIM_PATTERN)

        val materialKey = RandomUtil.pick(EquipmentGenerationSettings.LOOT_GOBLIN_ARMOR_TRIM_MATERIALS, random).option
        val material = materialRegistry.getOrThrow(materialKey)
        val patternKey = RandomUtil.pick(EquipmentGenerationSettings.LOOT_GOBLIN_ARMOR_TRIM_PATTERNS, random).option
        val pattern = patternRegistry.getOrThrow(patternKey)

        return ArmorTrim(material, pattern)
    }
}