package de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.attributes

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.setCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.util.random.RandomOption
import de.fuballer.mcendgame.main.util.random.RandomUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.item.ItemStack
import kotlin.random.Random

@Injectable
class AttributeService {
    fun applyAttributes(
        itemStack: ItemStack,
        attributes: List<RandomOption<RollableCustomAttribute>>,
        level: Int,
        random: Random,
        slot: AttributeModifierSlot,
    ) {
        val customAttributes = selectAttributes(level, attributes, random)
        itemStack.setCustomAttributes(customAttributes, slot)
    }

    private fun selectAttributes(
        level: Int,
        attributes: List<RandomOption<RollableCustomAttribute>>,
        random: Random,
    ): List<CustomAttribute> {
        val statAmount = RandomUtil.pick(AttributeSettings.ATTRIBUTE_COUNT, level, random).option

        val pickedAttributes = RandomUtil.pick(attributes, random, statAmount) // TODO check for duplicates
        val rolledAttributes = rollAttributes(pickedAttributes, random)

        return rolledAttributes
    }

    private fun rollAttributes(
        attributes: List<RollableCustomAttribute>,
        random: Random,
    ) = attributes
        .map {
            val percentageRolls = mutableListOf<Double>()
            repeat(it.bounds.size) {
                percentageRolls.add(random.nextDouble())
            }
            it.roll(percentageRolls)
        }
}