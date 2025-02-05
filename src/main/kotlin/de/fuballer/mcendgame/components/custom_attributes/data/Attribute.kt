package de.fuballer.mcendgame.components.custom_attributes.data

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import kotlin.random.Random

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

    fun roll(): CustomAttribute {
        val attributeRolls = bounds.map { it.roll(Random.nextDouble()) }
        return CustomAttribute(type, tier, attributeRolls)
    }

    fun roll(percentRoll: Double): CustomAttribute {
        val attributeRolls = bounds.map { it.roll(percentRoll) }
        return CustomAttribute(type, tier, attributeRolls)
    }

    fun roll(percentRolls: List<Double>): CustomAttribute {
        require(bounds.size == percentRolls.size) { "number of percentRolls must be equal to the number of bounds" }
        val attributeRolls = bounds.zip(percentRolls)
            .map { (attributeBounds, percentRoll) -> attributeBounds.roll(percentRoll) }

        return CustomAttribute(type, tier, attributeRolls)
    }
}

data class CustomAttribute(
    val type: AttributeType,
    val tier: Int,
    val rolls: List<AttributeRoll<*>>,
) {
    companion object {
        val CODEC: Codec<CustomAttribute> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    AttributeType.CODEC.fieldOf("type").forGetter(CustomAttribute::type),
                    Codec.INT.fieldOf("tier").forGetter(CustomAttribute::tier),
                    AttributeRoll.CODEC.listOf().fieldOf("rolls").forGetter(CustomAttribute::rolls)
                ).apply(instance, ::CustomAttribute)
            }
    }
}

data class BaseAttribute( // FIXME needed?
    val type: VanillaAttributeType,
    var amount: Double
)