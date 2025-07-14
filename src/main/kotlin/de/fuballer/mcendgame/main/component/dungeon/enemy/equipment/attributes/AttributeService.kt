package de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.attributes

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.setCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntRoll
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
        possibleAttributes: List<RandomOption<RollableCustomAttribute>>,
        level: Int,
        random: Random,
        slot: AttributeModifierSlot,
        lucky: Boolean,
    ) {
        val customAttributes = selectAttributes(level, possibleAttributes, random, lucky)
        itemStack.setCustomAttributes(customAttributes, slot)
    }

    private fun selectAttributes(
        level: Int,
        possibleAttributes: List<RandomOption<RollableCustomAttribute>>,
        random: Random,
        lucky: Boolean,
    ): List<CustomAttribute> {
        val rolledAttributes = getDistinctRolledAttributes(level, possibleAttributes, random)
        if (!lucky) return rolledAttributes

        val rolledAttributesB = getDistinctRolledAttributes(level, possibleAttributes, random)
        return getBetterRolls(rolledAttributes, rolledAttributesB)
    }

    private fun getDistinctRolledAttributes(
        level: Int,
        possibleAttributes: List<RandomOption<RollableCustomAttribute>>,
        random: Random,
    ): List<CustomAttribute> {
        val statAmount = RandomUtil.pick(AttributeSettings.ATTRIBUTE_COUNT, level, random).option
        val pickedAttributes = RandomUtil.pick(possibleAttributes, random, statAmount)
            .groupBy { it.type }.mapNotNull { (_, group) -> group.minByOrNull { it.tier } }
        return rollAttributes(pickedAttributes, random)
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

    private fun getBetterRolls(
        rollsA: List<CustomAttribute>,
        rollsB: List<CustomAttribute>
    ): List<CustomAttribute> {
        val tierScoreA = getTierScore(rollsA)
        val tierScoreB = getTierScore(rollsB)
        if (tierScoreA != tierScoreB) return if (tierScoreA > tierScoreB) rollsA else rollsB

        return if (getAvgPercentRoll(rollsA) >= getAvgPercentRoll(rollsB)) rollsA else rollsB
    }

    private fun getTierScore(rolls: List<CustomAttribute>) = rolls.sumOf { 5 - it.tier }

    private fun getAvgPercentRoll(rolledAttributes: List<CustomAttribute>): Double {
        val rolls = rolledAttributes.flatMap { it.rolls }.filter { it is IntRoll || it is DoubleRoll }
        val rollPercentSum = rolls.sumOf { if (it is DoubleRoll) it.asDoubleRoll().percentRoll else it.asIntRoll().percentRoll }
        return rollPercentSum / rolls.size
    }
}