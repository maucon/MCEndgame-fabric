package de.fuballer.mcendgame.main.component.custom_attribute.affinity

import de.fuballer.mcendgame.main.component.custom_attribute.data.AttributeRoll

abstract class AttributeAffinity() {
    abstract fun getAffinity(
        configuration: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ): Affinity
}

class SimpleAttributeAffinity(
    val affinity: Affinity,
) : AttributeAffinity() {
    override fun getAffinity(
        configuration: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ) = affinity
}

class ConditionalAttributeAffinity(
    val affinity: Affinity,
    val conditionIndex: Int,
) : AttributeAffinity() {
    override fun getAffinity(
        configuration: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ): Affinity {
        val conditionAffinity = configuration[conditionIndex].getAffinity(configuration, rolls, conditionIndex)
        val conditionSignAffinity = if (rolls[conditionIndex].isPositive()) conditionAffinity else conditionAffinity.invert()

        return if (conditionSignAffinity != Affinity.DETRIMENTAL) affinity else affinity.invert()
    }
}