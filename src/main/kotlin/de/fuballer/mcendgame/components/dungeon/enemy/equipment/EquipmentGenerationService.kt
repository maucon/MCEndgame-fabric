package de.fuballer.mcendgame.components.dungeon.enemy.equipment

import de.fuballer.mcendgame.components.dungeon.enemy.equipment.attributes.AttributeService
import de.fuballer.mcendgame.components.dungeon.enemy.equipment.enchantment.EnchantmentService
import de.fuballer.mcendgame.components.item.equipment.Equipment
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
            createMainHandItem(level, ranged, server, random)?.also {
                entity.equipStack(EquipmentSlot.MAINHAND, it)
            }
            createOffHandItem(level, server, random)?.also {
                entity.equipStack(EquipmentSlot.OFFHAND, it)
            }
        }

        if (!armor) return
        createEquipmentSortable(level, EquipmentGenerationSettings.HELMETS, server, random)?.also {
            entity.equipStack(EquipmentSlot.HEAD, it)
        }
        createEquipmentSortable(level, EquipmentGenerationSettings.CHESTPLATES, server, random)?.also {
            entity.equipStack(EquipmentSlot.CHEST, it)
        }
        createEquipmentSortable(level, EquipmentGenerationSettings.LEGGINGS, server, random)?.also {
            entity.equipStack(EquipmentSlot.LEGS, it)
        }
        createEquipmentSortable(level, EquipmentGenerationSettings.BOOTS, server, random)?.also {
            entity.equipStack(EquipmentSlot.FEET, it)
        }
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