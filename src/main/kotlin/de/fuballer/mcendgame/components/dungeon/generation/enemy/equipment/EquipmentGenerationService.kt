package de.fuballer.mcendgame.components.dungeon.generation.enemy.equipment

import de.fuballer.mcendgame.components.item.equipment.Equipment
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import de.fuballer.mcendgame.util.random.SortableRandomOption
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import kotlin.random.Random

@Injectable
class EquipmentGenerationService {
    fun generate(
        entity: LivingEntity,
        level: Int,
        weapons: Boolean,
        ranged: Boolean,
        armor: Boolean,
        random: Random,
    ) {
        if (weapons) {
            createMainHandItem(random, level, ranged)?.also {
                entity.equipStack(EquipmentSlot.MAINHAND, it)
            }
            createOffHandItem(random, level)?.also {
                entity.equipStack(EquipmentSlot.OFFHAND, it)
            }
        }

        if (!armor) return
        createEquipmentSortable(random, level, EquipmentGenerationSettings.HELMETS)?.also {
            entity.equipStack(EquipmentSlot.HEAD, it)
        }
        createEquipmentSortable(random, level, EquipmentGenerationSettings.CHESTPLATES)?.also {
            entity.equipStack(EquipmentSlot.CHEST, it)
        }
        createEquipmentSortable(random, level, EquipmentGenerationSettings.LEGGINGS)?.also {
            entity.equipStack(EquipmentSlot.LEGS, it)
        }
        createEquipmentSortable(random, level, EquipmentGenerationSettings.BOOTS)?.also {
            entity.equipStack(EquipmentSlot.FEET, it)
        }
    }

    private fun createMainHandItem(
        random: Random,
        level: Int,
        isRanged: Boolean,
    ): ItemStack? {
        val options = if (isRanged) {
            RandomUtil.pick(EquipmentGenerationSettings.RANGED_MAINHAND_PROBABILITIES, random).option
        } else {
            RandomUtil.pick(EquipmentGenerationSettings.MAINHAND_PROBABILITIES, random).option
        } ?: return null

        return createEquipmentSortable(random, level, options)
    }

    private fun createOffHandItem(
        random: Random,
        level: Int,
    ): ItemStack? {
        if (random.nextDouble() < EquipmentGenerationSettings.OFFHAND_OTHER_OVER_MAINHAND_PROBABILITY) {
            return createEquipment(random, level, EquipmentGenerationSettings.OTHER_ITEMS)
        }

        return createMainHandItem(random, level, false)
    }

    private fun createEquipmentSortable(
        random: Random,
        level: Int,
        equipmentOptions: List<SortableRandomOption<out Equipment?>>,
    ): ItemStack? {
        val rolls = EquipmentGenerationSettings.calculateEquipmentRollTries(level)
        val equipment = RandomUtil.pick(equipmentOptions, rolls, random).option ?: return null

        return createEquipment(random, level, equipment)
    }

    private fun createEquipment(
        random: Random,
        level: Int,
        equipmentOptions: List<RandomOption<out Equipment?>>,
    ): ItemStack? {
        val equipment = RandomUtil.pick(equipmentOptions, random).option ?: return null

        return createEquipment(random, level, equipment)
    }

    private fun createEquipment(
        random: Random,
        level: Int,
        equipment: Equipment,
    ): ItemStack {
        val item = equipment.item
        val itemStack = ItemStack(item)

        //TODO add enchants

        return itemStack
    }
}