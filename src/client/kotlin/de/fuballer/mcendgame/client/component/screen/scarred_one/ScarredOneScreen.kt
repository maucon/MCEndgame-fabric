package de.fuballer.mcendgame.client.component.screen.scarred_one

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.data.RolledScarredOneEffect
import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.networking.ScarredOneResponsePayload
import de.fuballer.mcendgame.main.util.ColorUtil
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.tooltip.TooltipBackgroundRenderer
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text
import java.util.*

private const val BACKGROUND_PADDING = 10
private const val ATTRIBUTE_LINE_OFFSET = 15
private const val BUTTON_WIDTH = 80
private const val BUTTON_HEIGHT = 20
private const val BUTTON_Y_OFFSET = 15
private const val BUTTON_CENTER_GAP = 20

class ScarredOneScreen(
    val positiveEffects: List<RolledScarredOneEffect>,
    val negativeEffects: List<RolledScarredOneEffect>,
    val uuid: UUID,
) : Screen(Text.empty()) {
    private var effectsTextData = listOf<EffectTextData>()
    private var backgroundWidth = 0
    private var backgroundHeight = 0
    private var backgroundX = 0
    private var backgroundY = 0

    override fun init() {
        super.init()

        setUp()

        addDrawableChild(
            ButtonWidget.builder(Text.literal("Accept")) {
                sendResponse(true)
            }.dimensions(width / 2 + BUTTON_CENTER_GAP / 2, backgroundY + backgroundHeight + BUTTON_Y_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build()
        )

        addDrawableChild(
            ButtonWidget.builder(Text.literal("Decline")) {
                sendResponse(false)
            }.dimensions(width / 2 - BUTTON_WIDTH - BUTTON_CENTER_GAP / 2, backgroundY + backgroundHeight + BUTTON_Y_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build()
        )
    }

    private fun setUp() {
        effectsTextData = positiveEffects.map {
            val text = it.getText(false)
            val detailedText = it.getText(true)
            EffectTextData(it, true, text, detailedText, textRenderer.getWidth(text.string), textRenderer.getWidth(detailedText.string))
        } + negativeEffects.map {
            val text = it.getText(false)
            val detailedText = it.getText(true)
            EffectTextData(it, false, text, detailedText, textRenderer.getWidth(text.string), textRenderer.getWidth(detailedText.string))
        }

        val maxTextWidth = effectsTextData.maxOf(EffectTextData::detailedWidth)
        backgroundWidth = maxTextWidth + 2 * BACKGROUND_PADDING
        backgroundHeight = (effectsTextData.size - 1) * ATTRIBUTE_LINE_OFFSET + textRenderer.fontHeight + 2 * BACKGROUND_PADDING
        backgroundX = (width / 2) - (backgroundWidth / 2)
        backgroundY = (height / 2) - (backgroundHeight / 2)
    }

    override fun render(
        context: DrawContext,
        mouseX: Int,
        mouseY: Int,
        delta: Float
    ) {
        super.render(context, mouseX, mouseY, delta)

        TooltipBackgroundRenderer.render(context, backgroundX, backgroundY, backgroundWidth, backgroundHeight, null)

        val detailed = MinecraftClient.getInstance().isShiftPressed
        effectsTextData.forEachIndexed { index, effect ->
            val text = if (detailed) effect.detailedText else effect.text
            val textWidth = if (detailed) effect.detailedWidth else effect.width
            context.drawText(
                textRenderer,
                text,
                (width / 2) - (textWidth / 2),
                backgroundY + BACKGROUND_PADDING + index * ATTRIBUTE_LINE_OFFSET,
                if (effect.positive) ColorUtil.rgbaToInt(0, 255, 0, 255) else ColorUtil.rgbaToInt(255, 0, 0, 255),
                false
            )
        }
    }

    private data class EffectTextData(
        val effect: RolledScarredOneEffect,
        val positive: Boolean,
        val text: Text,
        val detailedText: Text,
        val width: Int,
        val detailedWidth: Int,
    )

    override fun shouldPause(): Boolean = false

    fun sendResponse(accept: Boolean) {
        val payload = ScarredOneResponsePayload(accept, uuid)
        ClientPlayNetworking.send(payload)

        close()
    }
}