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
    ) = beneficial
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
        val conditionBeneficial = configuration[conditionIndex].isBeneficial(configuration, rolls, conditionIndex)
        val conditionSign = rolls[conditionIndex].isPositive()
        val condition = conditionBeneficial == conditionSign

        return beneficial == condition
    }
}