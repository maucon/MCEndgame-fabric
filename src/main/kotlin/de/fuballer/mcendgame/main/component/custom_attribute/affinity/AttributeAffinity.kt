package de.fuballer.mcendgame.main.component.custom_attribute.affinity

import de.fuballer.mcendgame.main.component.custom_attribute.data.AttributeRoll

abstract class AttributeAffinity() {
    abstract fun isBeneficial(
        configuration: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ): Boolean
}

class SimpleAttributeAffinity(
    val beneficial: Boolean,
) : AttributeAffinity() {
    override fun isBeneficial(
        configuration: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ): Boolean {
        val positive = rolls[index].isPositive()
        return beneficial == positive
    }
}

class ConditionalAttributeAffinity(
    val beneficial: Boolean,
    val conditionIndex: Int,
) : AttributeAffinity() {
    override fun isBeneficial(
        configuration: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ): Boolean {
        val condition = configuration[conditionIndex].isBeneficial(configuration, rolls, conditionIndex)
        val positive = rolls[index].isPositive()
        return beneficial == positive == condition
    }
}