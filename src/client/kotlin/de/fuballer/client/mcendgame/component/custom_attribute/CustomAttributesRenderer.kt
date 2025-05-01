package de.fuballer.client.mcendgame.component.custom_attribute

import de.fuballer.client.mcendgame.messaging.RenderItemTooltipCommand
import de.fuballer.mcendgame.component.custom_attribute.CustomAttributesExtensions.getCustomAttributes
import de.fuballer.mcendgame.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.util.NumberUtil
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import net.minecraft.util.Formatting

private const val LANGUAGE_KEY_PREFIX = "attribute.mcendgame."
private val ATTRIBUTE_LINE_COLOR = Formatting.BLUE
private val ATTRIBUTE_LINE_DETAILS_COLOR = Formatting.DARK_GRAY
private val TIER_DETAIL_COLOR = Formatting.AQUA

@Injectable
@Environment(EnvType.CLIENT)
class CustomAttributesRenderer {
    @CommandHandler
    fun on(cmd: RenderItemTooltipCommand) {
        val customAttributes = cmd.itemStack.getCustomAttributes()
        if (customAttributes.isEmpty()) return

        customAttributes
            .map { attribute ->
                if (Screen.hasShiftDown()) buildDetailedAttributeLine(attribute)
                else buildAttributeLine(attribute)
            }
            .forEach { cmd.texts.add(1, it) }
    }

    private fun buildDetailedAttributeLine(attribute: CustomAttribute): Text {
        val bounds = attribute.rolls.map { it.bounds }
        val formattedRolls = attribute.type.formatRolls(attribute.rolls)
        val formattedBounds = attribute.type.formatBounds(bounds)

        val detailedAttributeRolls = formattedRolls.zip(formattedBounds)
            .map {
                val boundsDetails = Text.literal(it.second)
                    .formatted(ATTRIBUTE_LINE_DETAILS_COLOR)

                Text.literal(it.first)
                    .formatted(ATTRIBUTE_LINE_COLOR)
                    .append(boundsDetails)
            }

        val romanNumeralTier = NumberUtil.toRomanNumeral(attribute.tier)

        val tierDetails = Text.literal(" $romanNumeralTier")
            .formatted(TIER_DETAIL_COLOR)

        return Text.translatable("$LANGUAGE_KEY_PREFIX${attribute.type.key}", *detailedAttributeRolls.toTypedArray())
            .formatted(ATTRIBUTE_LINE_COLOR)
            .append(tierDetails)
    }

    private fun buildAttributeLine(attribute: CustomAttribute): Text {
        val formattedRolls = attribute.type.formatRolls(attribute.rolls).toTypedArray()

        return Text.translatable("$LANGUAGE_KEY_PREFIX${attribute.type.key}", *formattedRolls)
            .formatted(ATTRIBUTE_LINE_COLOR)
    }
}