package de.fuballer.mcendgame.main.component.custom_attribute.data

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.fuballer.mcendgame.main.component.custom_attribute.affinity.AttributeAffinity
import de.fuballer.mcendgame.main.util.minecraft.CodecUtil
import kotlin.math.roundToInt
import kotlin.random.Random

sealed interface AttributeRoll<T> {
    companion object {
        val CODEC: Codec<AttributeRoll<*>> = CodecUtil.ofThree(DoubleRoll.CODEC, StringRoll.CODEC, IntRoll.CODEC)
        const val DEFAULT_BENEFICIAL_ROLL_PERCENTAGE = 0.5
    }

    val bounds: AttributeBounds<*>

    fun getValue(): T

    fun isNegative(): Boolean

    fun isPositive() = !isNegative()

    fun getSignFlipped(): AttributeRoll<T>

    fun hasNonZeroRange(): Boolean

    fun getRerolled(): AttributeRoll<T>

    fun getAffinityBasedRollPercentage(
        config: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ): Double //TODO add system to determine if attributes / rolls are beneficial

    fun getEnhanced(
        value: Double,
        beneficial: Boolean,
    ): AttributeRoll<T>
}

data class DoubleRoll(
    override val bounds: DoubleBounds,
    var percentRoll: Double,
    val format: String = "%.0f",
) : AttributeRoll<Double> {
    companion object {
        val CODEC: Codec<DoubleRoll> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    DoubleBounds.CODEC.fieldOf("bounds").forGetter(DoubleRoll::bounds),
                    Codec.DOUBLE.fieldOf("percentRoll").forGetter(DoubleRoll::percentRoll)
                ).apply(instance, ::DoubleRoll)
            }
    }

    override fun getValue() = bounds.min + (bounds.max - bounds.min) * percentRoll

    override fun isNegative() = getValue() < 0

    override fun getSignFlipped() = DoubleRoll(bounds.getSignFlipped(), 1 - percentRoll, format)

    override fun hasNonZeroRange() = bounds.min < bounds.max

    override fun getRerolled() = DoubleRoll(bounds, Random.nextDouble(), format)

    override fun getAffinityBasedRollPercentage(
        config: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ): Double {
        val beneficial = config[index].isBeneficial(config, rolls, index)
        val percentage = if (bounds.min == bounds.max) AttributeRoll.DEFAULT_BENEFICIAL_ROLL_PERCENTAGE else percentRoll
        return if (beneficial) percentage else 1 - percentage
    }

    override fun getEnhanced(
        value: Double,
        beneficial: Boolean,
    ): DoubleRoll {
        val enhancedPercentRoll = if (beneficial) percentRoll + value else percentRoll - value
        return DoubleRoll(bounds, enhancedPercentRoll, format)
    }
}

data class StringRoll(
    override val bounds: StringBounds,
    var indexRoll: Int,
) : AttributeRoll<String> {
    companion object {
        val CODEC: Codec<StringRoll> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    StringBounds.CODEC.fieldOf("bounds").forGetter(StringRoll::bounds),
                    Codec.INT.fieldOf("indexRoll").forGetter(StringRoll::indexRoll)
                ).apply(instance, ::StringRoll)
            }
    }

    override fun getValue() = bounds.options[indexRoll]

    override fun isNegative() = false

    override fun getSignFlipped() = StringRoll(bounds.getSignFlipped(), indexRoll)

    override fun hasNonZeroRange() = bounds.options.size > 1

    override fun getRerolled() = StringRoll(bounds, Random.nextInt(bounds.options.size))

    override fun getAffinityBasedRollPercentage(
        config: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ) = AttributeRoll.DEFAULT_BENEFICIAL_ROLL_PERCENTAGE

    override fun getEnhanced(
        value: Double,
        beneficial: Boolean,
    ) = copy()
}

data class IntRoll(
    override val bounds: IntBounds,
    var percentRoll: Double
) : AttributeRoll<Int> {
    companion object {
        val CODEC: Codec<IntRoll> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    IntBounds.CODEC.fieldOf("bounds").forGetter(IntRoll::bounds),
                    Codec.DOUBLE.fieldOf("percentRollI").forGetter(IntRoll::percentRoll) // percentRollI to differentiate between DoubleRoll
                ).apply(instance, ::IntRoll)
            }
    }

    override fun getValue() = (bounds.min + (bounds.max - bounds.min) * percentRoll).roundToInt()

    override fun isNegative() = getValue() < 0

    override fun getSignFlipped() = IntRoll(bounds.getSignFlipped(), 1 - percentRoll)

    override fun hasNonZeroRange() = bounds.min < bounds.max

    override fun getRerolled() = IntRoll(bounds, Random.nextDouble())

    override fun getAffinityBasedRollPercentage(
        config: List<AttributeAffinity>,
        rolls: List<AttributeRoll<*>>,
        index: Int,
    ): Double {
        val beneficial = config[index].isBeneficial(config, rolls, index)
        val percentage = if (bounds.min == bounds.max) AttributeRoll.DEFAULT_BENEFICIAL_ROLL_PERCENTAGE else percentRoll
        return if (beneficial) percentage else 1 - percentage
    }

    override fun getEnhanced(
        value: Double,
        beneficial: Boolean,
    ): IntRoll {
        val enhancedPercentRoll = if (beneficial) percentRoll + value else percentRoll - value
        return IntRoll(bounds, enhancedPercentRoll)
    }
}