package de.fuballer.mcendgame.component.dungeon.enemy.equipment

import de.fuballer.mcendgame.component.dungeon.enemy.equipment.attributes.AttributeService
import de.fuballer.mcendgame.component.dungeon.enemy.equipment.enchantment.EnchantmentService
import de.fuballer.mcendgame.component.item.equipment.Equipment
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import de.fuballer.mcendgame.util.random.SortableRandomOption
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import kotlin.random.Random

@Injectable
class EquipmentGenerationService(
    private val enchantmentService: EnchantmentService,
    private val attributeService: AttributeService,
) {
    fun generate(
        entity: LivingEntity,
        level: Int,
        weapons: Boolean,
        ranged: Boolean,
        armor: Boolean,
        server: MinecraftServer,
        random: Random,
    ) {
        if (weapons) {
            createEquipment(level, EquipmentSlot.MAINHAND, server, random, ranged)?.also {
                entity.equipStack(EquipmentSlot.MAINHAND, it)
            }
            createEquipment(level, EquipmentSlot.OFFHAND, server, random)?.also {
                entity.equipStack(EquipmentSlot.OFFHAND, it)
            }
        }

        if (!armor) return
        createEquipment(level, EquipmentSlot.HEAD, server, random)?.also {
            entity.equipStack(EquipmentSlot.HEAD, it)
        }
        createEquipment(level, EquipmentSlot.CHEST, server, random)?.also {
            entity.equipStack(EquipmentSlot.CHEST, it)
        }
        createEquipment(level, EquipmentSlot.LEGS, server, random)?.also {
            entity.equipStack(EquipmentSlot.LEGS, it)
        }
        createEquipment(level, EquipmentSlot.FEET, server, random)?.also {
            entity.equipStack(EquipmentSlot.FEET, it)
        }
    }

    private fun createEquipment(
        level: Int,
        slot: EquipmentSlot,
        server: MinecraftServer,
        random: Random,
        isRanged: Boolean = false,
    ): ItemStack? {
        if (random.nextDouble() <= EquipmentGenerationSettings.UNIQUE_EQUIPMENT_PROBABILITY) {
            return createUniqueEquipment(level, slot, server, random)
        }

        return when (slot) {
            EquipmentSlot.MAINHAND -> createMainHandItem(level, isRanged, server, random)
            EquipmentSlot.OFFHAND -> createOffHandItem(level, server, random)
            else -> createArmorEquipment(level, slot, server, random)
        }
    }

    private fun createUniqueEquipment(
        level: Int,
        slot: EquipmentSlot,
        server: MinecraftServer,
        random: Random,
    ): ItemStack? {
        val equipment = EquipmentGenerationSettings.UNIQUE_EQUIPMENT[slot]?.random(random) ?: return null
        val itemStack = ItemStack(equipment.item)

        enchantmentService.enchantItem(itemStack, equipment.rollableEnchants, level, server, random)
        return itemStack
    }

    private fun createMainHandItem(
        level: Int,
        isRanged: Boolean,
        server: MinecraftServer,
        random: Random,
    ): ItemStack? {
        val options = if (isRanged) {
            RandomUtil.pick(EquipmentGenerationSettings.RANGED_MAINHAND_PROBABILITIES, random).option
        } else {
            RandomUtil.pick(EquipmentGenerationSettings.MAINHAND_PROBABILITIES, random).option
        } ?: return null

        return createEquipmentSortable(level, options, server, random)
    }

    private fun createOffHandItem(
        level: Int,
        server: MinecraftServer,
        random: Random,
    ): ItemStack? {
        if (random.nextDouble() < EquipmentGenerationSettings.OFFHAND_OTHER_OVER_MAINHAND_PROBABILITY) {
            return createEquipment(level, EquipmentGenerationSettings.OTHER_ITEMS, server, random)
        }

        return createMainHandItem(level, false, server, random)
    }

    private fun createArmorEquipment(
        level: Int,
        slot: EquipmentSlot,
        server: MinecraftServer,
        random: Random,
    ): ItemStack? {
        val equipmentOptions = EquipmentGenerationSettings.ARMORSLOT_EQUIPMENT_MAP[slot] ?: return null
        return createEquipmentSortable(level, equipmentOptions, server, random)
    }

    private fun createEquipmentSortable(
        level: Int,
        equipmentOptions: List<SortableRandomOption<out Equipment?>>,
        server: MinecraftServer,
        random: Random,
    ): ItemStack? {
        val rolls = EquipmentGenerationSettings.calculateEquipmentRollTries(level)
        val equipment = RandomUtil.pick(equipmentOptions, rolls, random).option ?: return null

        return createEquipment(level, equipment, server, random)
    }

    private fun createEquipment(
        level: Int,
        equipmentOptions: List<RandomOption<out Equipment?>>,
        server: MinecraftServer,
        random: Random,
    ): ItemStack? {
        val equipment = RandomUtil.pick(equipmentOptions, random).option ?: return null

        return createEquipment(level, equipment, server, random)
    }

    private fun createEquipment(
        level: Int,
        equipment: Equipment,
        server: MinecraftServer,
        random: Random,
    ): ItemStack {
        val item = equipment.item
        val itemStack = ItemStack(item)

        enchantmentService.enchantItem(itemStack, equipment.rollableEnchants, level, server, random)
        attributeService.applyAttributes(itemStack, equipment.rollableCustomAttributes, level, random, equipment.slot)

        return itemStack
    }
}