package de.fuballer.mcendgame.client.component.screen

import net.minecraft.client.MinecraftClient
import net.minecraft.client.gl.RenderPipelines
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffectUtil
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import net.minecraft.util.Colors
import net.minecraft.util.Identifier
import java.util.*

private val EFFECT_BACKGROUND_TEXTURE: Identifier = Identifier.ofVanilla("container/inventory/effect_background")
private val AMBIENT_EFFECT_BACKGROUND_TEXTURE: Identifier = Identifier.ofVanilla("container/inventory/effect_background_ambient")

class CustomStatusEffectsDisplay(
    val parent: HandledScreen<*>,
    val client: MinecraftClient = MinecraftClient.getInstance(),
) {
    var backgroundHeight = 32
    var wideWidth = 120
    var smallWidth = 32
    var isWide: (Int) -> Boolean = { space -> space >= 120 }

    var spriteSize = 18
    var spriteXOffset: (Boolean) -> Int = { wide -> if (wide) 6 else 7 }
    var spriteYOffset = 7

    var textXOffset = 28
    var descriptionTextYOffset = 6
    var descriptionTextColor = Colors.WHITE
    var durationTextYOffset = 16
    var durationTextColor = Colors.GRAY
    var renderDurationText = true

    var enableTooltip = true

    var yOffsetPerEffect: (Int) -> Int = { effectCount -> if (effectCount <= 5) 33 else 132 / (effectCount - 1) }

    fun drawStatusEffects(
        context: DrawContext,
        x: Int,
        y: Int,
        mouseX: Int,
        mouseY: Int,
        statusEffects: Collection<StatusEffectInstance>,
    ) {
        val space = parent.width - x
        if (space < 32) return
        if (statusEffects.isEmpty()) return

        val wide = isWide(space)
        val yOffsetPerEffect = yOffsetPerEffect(statusEffects.size)

        val sortedEffects = statusEffects.sortedBy { it }
        var effectY = y
        sortedEffects.forEach {
            drawStatusEffectBackground(context, x, effectY, wide, it.isAmbient)
            drawStatusEffectSprite(context, x, effectY, it, wide)

            if (wide) drawStatusEffectDescription(context, x, effectY, it)
            effectY += yOffsetPerEffect
        }

        if (wide || !enableTooltip) return
        drawTooltip(context, sortedEffects, x, y, mouseX, mouseY, yOffsetPerEffect)
    }

    private fun drawTooltip(
        context: DrawContext,
        effects: Iterable<StatusEffectInstance>,
        x: Int,
        y: Int,
        mouseX: Int,
        mouseY: Int,
        yOffsetPerEffect: Int,
    ) {
        if (mouseX < x || mouseX > x + smallWidth) return

        var yy = y
        var hoveredStatusEffectInstance: StatusEffectInstance? = null
        effects.forEach {
            if (mouseY >= yy && mouseY <= yy + yOffsetPerEffect) {
                hoveredStatusEffectInstance = it
            }
            yy += yOffsetPerEffect
        }
        if (hoveredStatusEffectInstance == null) return

        val tooltip = mutableListOf(getStatusEffectDescription(hoveredStatusEffectInstance))
        if (renderDurationText) tooltip.add(StatusEffectUtil.getDurationText(hoveredStatusEffectInstance, 1.0F, client.world!!.tickManager.getTickRate()))

        context.drawTooltip(parent.getTextRenderer(), tooltip, Optional.empty(), mouseX, mouseY)
    }

    private fun drawStatusEffectBackground(
        context: DrawContext,
        x: Int,
        yBase: Int,
        wide: Boolean,
        ambient: Boolean,
    ) {
        context.drawGuiTexture(
            RenderPipelines.GUI_TEXTURED,
            if (ambient) AMBIENT_EFFECT_BACKGROUND_TEXTURE else EFFECT_BACKGROUND_TEXTURE,
            x,
            yBase,
            if (wide) wideWidth else smallWidth,
            backgroundHeight
        )
    }

    private fun drawStatusEffectSprite(
        context: DrawContext,
        x: Int,
        yBase: Int,
        statusEffect: StatusEffectInstance,
        wide: Boolean,
    ) {
        val sprite = InGameHud.getEffectTexture(statusEffect.effectType)
        context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, sprite, x + spriteXOffset(wide), yBase + spriteYOffset, spriteSize, spriteSize)
    }

    private fun drawStatusEffectDescription(
        context: DrawContext,
        x: Int,
        yBase: Int,
        statusEffect: StatusEffectInstance,
    ) {
        val descriptionText = getStatusEffectDescription(statusEffect)
        context.drawTextWithShadow(parent.textRenderer, descriptionText, x + textXOffset, yBase + descriptionTextYOffset, descriptionTextColor)

        if (renderDurationText) {
            val durationText = StatusEffectUtil.getDurationText(statusEffect, 1.0F, client.world!!.tickManager.getTickRate())
            context.drawTextWithShadow(parent.textRenderer, durationText, x + textXOffset, yBase + durationTextYOffset, durationTextColor)
        }
    }

    private fun getStatusEffectDescription(
        statusEffect: StatusEffectInstance,
    ): Text {
        val text = (statusEffect.effectType.value() as StatusEffect).name.copy()
        if (statusEffect.amplifier !in 1..9) return text
        return text.append(ScreenTexts.SPACE).append(Text.translatable("enchantment.level." + (statusEffect.amplifier + 1)))
    }
}