package de.fuballer.mcendgame.client.component.custom_attribute

import de.fuballer.mcendgame.client.messaging.RenderItemTooltipCommand
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.AttributeRoll
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.sign_based_keyword.SignBasedKeyword
import de.fuballer.mcendgame.main.util.NumberUtil
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
private val BOUNDS_REGEX = Regex("""\((-?[\d.]+)-(-?[\d.]+)\)""")

@Injectable
@Environment(EnvType.CLIENT)
class CustomAttributesRenderer {
    @CommandHandler
    fun on(cmd: RenderItemTooltipCommand) {
        val customAttributes = cmd.itemStack.getCustomAttributes()
        if (customAttributes.isEmpty()) return

        customAttributes
            .reversed()
            .map { attribute -> buildAttributeLine(attribute, Screen.hasShiftDown()) }
            .forEach { cmd.texts.add(1, it) }
    }

    private fun buildAttributeLine(attribute: CustomAttribute, detailed: Boolean): Text {
        val type = attribute.type
        val rolls = attribute.rolls
        val signBasedKeywords = type.signBasedKeywords
        val flipSign = signBasedKeywords.zip(rolls).map { it.first != null && it.second.isNegative() }

        val flippedRolls = flipRollSigns(rolls, flipSign)
        val formattedRolls = type.formatRolls(flippedRolls)
        if (!detailed) {
            val array = getWithSignBasedKeywords(formattedRolls.map { Text.literal(it) }, signBasedKeywords, flipSign).toTypedArray()
            return Text.translatable("$LANGUAGE_KEY_PREFIX${type.key}", *array)
                .formatted(ATTRIBUTE_LINE_COLOR)
        }

        val formattedBounds = type.formatBounds(flippedRolls.map { it.bounds })
        val formattedRollsWithBounds = formattedRolls.zip(formattedBounds)
            .map {
                val boundsDetails = Text.literal(it.second)
                    .formatted(ATTRIBUTE_LINE_DETAILS_COLOR)

                Text.literal(it.first)
                    .formatted(ATTRIBUTE_LINE_COLOR)
                    .append(boundsDetails)
            }
        val array = getWithSignBasedKeywords(formattedRollsWithBounds, signBasedKeywords, flipSign).toTypedArray()

        return Text.translatable("$LANGUAGE_KEY_PREFIX${type.key}", *array)
            .formatted(ATTRIBUTE_LINE_COLOR)
            .append(getFormattedTier(attribute.tier))
    }

    private fun flipRollSigns(rolls: List<AttributeRoll<*>>, flip: List<Boolean>) =
        rolls.mapIndexed { i, roll ->
            if (flip.getOrNull(i) != true) roll
            else roll.getSignFlipped()
        }

    private fun getWithSignBasedKeywords(
        rolls: List<Text>,
        keywords: List<SignBasedKeyword?>,
        isNegative: List<Boolean>,
    ): List<Text> {
        if (keywords.isEmpty()) return rolls

        val insertedList = rolls.toMutableList()
        var insertedCount = 0
        keywords.forEachIndexed { i, keyword ->
            if (keyword == null) return@forEachIndexed

            val text = if (isNegative[i]) keyword.negative else keyword.positive
            val index = i + insertedCount + 1
            insertedList.add(index, text)

            insertedCount++
        }

        return insertedList
    }

    private fun getFormattedTier(tier: Int) = Text.literal(" ${NumberUtil.toRomanNumeral(tier)}")
        .formatted(TIER_DETAIL_COLOR)
}