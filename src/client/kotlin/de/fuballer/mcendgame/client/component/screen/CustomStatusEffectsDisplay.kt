package de.fuballer.mcendgame.client.component.screen

import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffectUtil
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.*

val EFFECT_BACKGROUND_LARGE_TEXTURE: Identifier = Identifier.ofVanilla("container/inventory/effect_background_large");
val EFFECT_BACKGROUND_SMALL_TEXTURE: Identifier = Identifier.ofVanilla("container/inventory/effect_background_small");

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
    var descriptionTextColor = 16777215
    var durationTextYOffset = 16
    var durationTextColor = 8355711
    var renderDurationText = true

    var yOffset: (Int) -> Int = { effectCount -> if (effectCount <= 5) 33 else 132 / (effectCount - 1) }

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
        var yOffsetPerEffect = yOffset(statusEffects.size)

        val sortedEffects = statusEffects.sortedBy { it }
        drawStatusEffectBackgrounds(context, x, y, yOffsetPerEffect, sortedEffects, wide)
        drawStatusEffectSprites(context, x, y, yOffsetPerEffect, sortedEffects, wide)

        if (wide) return drawStatusEffectDescriptions(context, x, y, yOffsetPerEffect, sortedEffects)

        if (mouseX < x || mouseX > x + smallWidth) return

        var yy = y
        var hoveredStatusEffectInstance: StatusEffectInstance? = null
        sortedEffects.forEach {
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

    private fun drawStatusEffectBackgrounds(
        context: DrawContext,
        x: Int,
        yStart: Int,
        yOffsetPerEffect: Int,
        statusEffects: Iterable<StatusEffectInstance>,
        wide: Boolean,
    ) {
        var y = yStart

        statusEffects.forEach {
            if (wide) context.drawGuiTexture(RenderLayer::getGuiTextured, EFFECT_BACKGROUND_LARGE_TEXTURE, x, y, wideWidth, backgroundHeight)
            else context.drawGuiTexture(RenderLayer::getGuiTextured, EFFECT_BACKGROUND_SMALL_TEXTURE, x, y, smallWidth, backgroundHeight)

            y += yOffsetPerEffect
        }
    }

    private fun drawStatusEffectSprites(
        context: DrawContext,
        x: Int,
        yStart: Int,
        yOffsetPerEffect: Int,
        statusEffects: Iterable<StatusEffectInstance>,
        wide: Boolean,
    ) {
        val statusEffectSpriteManager = client.statusEffectSpriteManager
        var y = yStart

        statusEffects.forEach {
            val entry = it.effectType
            val sprite = statusEffectSpriteManager.getSprite(entry)
            context.drawSpriteStretched(RenderLayer::getGuiTextured, sprite, x + spriteXOffset(wide), y + spriteYOffset, spriteSize, spriteSize)

            y += yOffsetPerEffect
        }
    }

    private fun drawStatusEffectDescriptions(
        context: DrawContext,
        x: Int,
        yStart: Int,
        yOffsetPerEffect: Int,
        statusEffects: Iterable<StatusEffectInstance>,
    ) {
        var y = yStart

        statusEffects.forEach {
            val descriptionText = getStatusEffectDescription(it)
            context.drawTextWithShadow(parent.getTextRenderer(), descriptionText, x + textXOffset, y + descriptionTextYOffset, descriptionTextColor)

            if (renderDurationText) {
                val durationText = StatusEffectUtil.getDurationText(it, 1.0F, client.world!!.tickManager.getTickRate())
                context.drawTextWithShadow(parent.getTextRenderer(), durationText, x + textXOffset, y + durationTextYOffset, durationTextColor)
            }

            y += yOffsetPerEffect
        }
    }

    private fun getStatusEffectDescription(
        statusEffect: StatusEffectInstance,
    ): Text {
        val text = (statusEffect.effectType.value() as StatusEffect).name.copy()
        if (statusEffect.amplifier < 1 || statusEffect.amplifier > 9) return text
        return text.append(ScreenTexts.SPACE).append(Text.translatable("enchantment.level." + (statusEffect.amplifier + 1)))
    }
}