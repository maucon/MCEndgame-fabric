package de.fuballer.mcendgame.main.component.custom_attribute

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntBounds
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asStringBounds
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asStringRoll
import de.fuballer.mcendgame.main.component.custom_attribute.data.AttributeBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.AttributeRoll

object AttributeFormats {
    val EMPTY_ROLL = { _: List<AttributeRoll<*>> -> listOf<String>() }
    val PERCENT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(formatDouble(rolls[0].asDoubleRoll().getValue() * 100))
    }
    val SIGNED_PERCENT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(formatDoubleSigned(rolls[0].asDoubleRoll().getValue() * 100))
    }
    val STRING_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(rolls[0].asStringRoll().getValue())
    }
    val TWO_STRING_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(rolls[0].asStringRoll().getValue(), rolls[1].asStringRoll().getValue())
    }
    val INT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(rolls[0].getValue().toString())
    }
    val SIGNED_INT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(String.format("%+d", rolls[0].asIntRoll().getValue()))
    }
    val TWO_INT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(rolls[0].getValue().toString(), rolls[1].getValue().toString())
    }
    val DOUBLE_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(formatDouble(rolls[0].asDoubleRoll().getValue()))
    }
    val SIGNED_DOUBLE_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(formatDoubleSigned(rolls[0].asDoubleRoll().getValue()))
    }
    val PERCENT_AND_INT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(formatDouble(rolls[0].asDoubleRoll().getValue() * 100), rolls[1].getValue().toString())
    }
    val INT_AND_PERCENT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(rolls[0].getValue().toString(), formatDouble(rolls[1].asDoubleRoll().getValue() * 100))
    }
    val SIGNED_PERCENT_AND_INT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(formatDoubleSigned(rolls[0].asDoubleRoll().getValue() * 100), rolls[1].getValue().toString())
    }

    val EMPTY_BOUNDS = { _: List<AttributeBounds<*>> -> listOf<String>() }
    val PERCENT_BOUNDS = { bounds: List<AttributeBounds<*>> ->
        val bound = bounds[0].asDoubleBounds()
        listOf("(${formatDouble(bound.min * 100)}-${formatDouble(bound.max * 100)})")
    }
    val STRING_SHOW_ALL_OPTIONS = { bounds: List<AttributeBounds<*>> ->
        listOf(bounds[0].asStringBounds().options.joinToString(prefix = "(", postfix = ")"))
    }
    val TWO_STRING_SHOW_ALL_OPTIONS = { bounds: List<AttributeBounds<*>> ->
        listOf(
            bounds[0].asStringBounds().options.joinToString(prefix = "(", postfix = ")"),
            bounds[1].asStringBounds().options.joinToString(prefix = "(", postfix = ")"),
        )
    }
    val INT_BOUNDS = { bounds: List<AttributeBounds<*>> ->
        val bound = bounds[0].asIntBounds()
        listOf(String.format("(%d-%d)", bound.min, bound.max))
    }
    val TWO_INT_BOUNDS = { bounds: List<AttributeBounds<*>> ->
        val bound1 = bounds[0].asIntBounds()
        val bound2 = bounds[1].asIntBounds()
        listOf(String.format("(%d-%d)", bound1.min, bound1.max), String.format("(%d-%d)", bound2.min, bound2.max))
    }
    val DOUBLE_BOUNDS = { bounds: List<AttributeBounds<*>> ->
        val bound = bounds[0].asDoubleBounds()
        listOf("(${formatDouble(bound.min)}-${formatDouble(bound.max)})")
    }
    val PERCENT_AND_INT_BOUNDS = { bounds: List<AttributeBounds<*>> ->
        val bound1 = bounds[0].asDoubleBounds()
        val bound2 = bounds[1].asIntBounds()
        listOf("(${formatDouble(bound1.min * 100)}-${formatDouble(bound1.max * 100)})", String.format("(%d-%d)", bound2.min, bound2.max))
    }
    val INT_AND_PERCENT_BOUNDS = { bounds: List<AttributeBounds<*>> ->
        val bound1 = bounds[0].asIntBounds()
        val bound2 = bounds[1].asDoubleBounds()
        listOf(String.format("(%d-%d)", bound1.min, bound1.max), "(${formatDouble(bound2.min * 100)}-${formatDouble(bound2.max * 100)})")
    }

    fun formatDouble(value: Double): String {
        val formatted = "%.1f".format(value)
        if (formatted.endsWith("0")) {
            return "%.0f".format(value)
        }
        return formatted
    }

    private fun formatDoubleSigned(value: Double): String {
        val formatted = "%+.1f".format(value)
        if (formatted.endsWith("0")) {
            return "%+.0f".format(value)
        }
        return formatted
    }
}