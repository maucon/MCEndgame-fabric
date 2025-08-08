package de.fuballer.mcendgame.main.component.custom_attribute.data

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.component.type.AttributeModifierSlot

data class RollableCustomAttribute(
    val type: AttributeType,
    val tier: Int,
    val bounds: List<AttributeBounds<*>>,
) {
    constructor(
        type: AttributeType,
        tier: Int,
        vararg bounds: AttributeBounds<*>
    ) : this(type, tier, bounds.toList())

    fun roll(percentRolls: List<Double>, slot: AttributeModifierSlot): CustomAttribute {
        require(bounds.size == percentRolls.size) { "number of percentRolls must be equal to the number of bounds" }
        val attributeRolls = bounds.zip(percentRolls)
            .map { (attributeBounds, percentRoll) -> attributeBounds.roll(percentRoll) }

        return CustomAttribute(type, tier, attributeRolls, slot)
    }
}

data class CustomAttribute(
    val type: AttributeType,
    val tier: Int,
    val rolls: List<AttributeRoll<*>>,
    val slot: AttributeModifierSlot,
) {
    companion object {
        val CODEC: Codec<CustomAttribute> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    AttributeType.CODEC.fieldOf("type").forGetter(CustomAttribute::type),
                    Codec.INT.fieldOf("tier").forGetter(CustomAttribute::tier),
                    AttributeRoll.CODEC.listOf().fieldOf("rolls").forGetter(CustomAttribute::rolls),
                    AttributeModifierSlot.CODEC.fieldOf("slot").forGetter(CustomAttribute::slot),
                ).apply(instance, ::CustomAttribute)
            }
    }

    fun hasNonZeroRange() = rolls.any { it.hasNonZeroRange() }

    fun getRerolled() = CustomAttribute(type, tier, rolls.map { it.getRerolled() }, slot)

    fun getAffinityBasedRollPercentage(): Double {
        if (rolls.isEmpty()) return AttributeRoll.DEFAULT_BENEFICIAL_ROLL_PERCENTAGE
        return rolls.withIndex().map { it.value.getAffinityBasedRollPercentage(type.affinities, rolls, it.index) }.average()
    }

    fun getEnhanced(value: Double): CustomAttribute {
        val enhancedRolls = rolls.withIndex().map {
            val beneficial = type.affinities[it.index].isBeneficial(type.affinities, rolls, it.index)
            it.value.getEnhanced(value, beneficial)
        }
        return CustomAttribute(type, tier, enhancedRolls, slot)
    }
}