package de.fuballer.mcendgame.component.custom_attribute.data

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.fuballer.mcendgame.util.CodecUtil
import kotlin.math.roundToInt

sealed interface AttributeRoll<T> {
    companion object {
        val CODEC: Codec<AttributeRoll<*>> = CodecUtil.ofThree(DoubleRoll.CODEC, StringRoll.CODEC, IntRoll.CODEC)
    }

    val bounds: AttributeBounds<*>

    fun getActualRoll(): T
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

    override fun getActualRoll() = bounds.min + (bounds.max - bounds.min) * percentRoll
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

    override fun getActualRoll() = bounds.options[indexRoll]
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

    override fun getActualRoll() = (bounds.min + (bounds.max - bounds.min) * percentRoll).roundToInt()
}