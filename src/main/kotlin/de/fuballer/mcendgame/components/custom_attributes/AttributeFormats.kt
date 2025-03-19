package de.fuballer.mcendgame.components.custom_attributes

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleBounds
import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asIntBounds
import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asStringBounds
import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asStringRoll
import de.fuballer.mcendgame.components.custom_attributes.data.AttributeBounds
import de.fuballer.mcendgame.components.custom_attributes.data.AttributeRoll

object AttributeFormats {
    val EMPTY_ROLL = { _: List<AttributeRoll<*>> -> listOf<String>() }
    val PERCENT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(String.format("%.0f", rolls[0].asDoubleRoll().getActualRoll() * 100))
    }
    val SIGNED_PERCENT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(String.format("%+.0f", rolls[0].asDoubleRoll().getActualRoll() * 100))
    }
    val STRING_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(rolls[0].asStringRoll().getActualRoll())
    }
    val INT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(rolls[0].getActualRoll().toString())
    }
    val SIGNED_INT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(String.format("%+d", rolls[0].asIntRoll().getActualRoll()))
    }
    val TWO_INT_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(rolls[0].getActualRoll().toString(), rolls[1].getActualRoll().toString())
    }
    val SIGNED_DOUBLE_ROLL = { rolls: List<AttributeRoll<*>> ->
        listOf(String.format("%+.0f", rolls[0].asDoubleRoll().getActualRoll()))
    }

    val EMPTY_BOUNDS = { _: List<AttributeBounds<*>> -> listOf<String>() }
    val PERCENT_BOUNDS = { bounds: List<AttributeBounds<*>> ->
        val bound = bounds[0].asDoubleBounds()
        listOf(String.format("(%.0f-%.0f)", bound.min * 100, bound.max * 100))
    }
    val STRING_SHOW_ALL_OPTIONS = { bounds: List<AttributeBounds<*>> ->
        listOf(bounds[0].asStringBounds().options.joinToString(prefix = "(", postfix = ")"))
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
        listOf(String.format("(%.0f-%.0f)", bound.min, bound.max))
    }
}