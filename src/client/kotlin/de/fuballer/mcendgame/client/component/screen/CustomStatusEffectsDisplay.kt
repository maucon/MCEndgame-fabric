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

        val wide = space >= 120
        val statusEffectCount = statusEffects.size
        var yOffsetPerEffect = if (statusEffectCount <= 5) 33 else 132 / (statusEffectCount - 1)

        val sortedEffects = statusEffects.sortedBy { it }
        drawStatusEffectBackgrounds(context, x, y, yOffsetPerEffect, sortedEffects, wide)
        drawStatusEffectSprites(context, x, y, yOffsetPerEffect, sortedEffects, wide)
        if (wide) return drawStatusEffectDescriptions(context, x, y, yOffsetPerEffect, sortedEffects)

        if (mouseX < x || mouseX > x + 33) return

        var yy = y
        var hoveredStatusEffectInstance: StatusEffectInstance? = null
        sortedEffects.forEach {
            if (mouseY >= yy && mouseY <= yy + yOffsetPerEffect) {
                hoveredStatusEffectInstance = it
            }
            yy += yOffsetPerEffect
        }
        if (hoveredStatusEffectInstance == null) return

        val tooltip = listOf(
            getStatusEffectDescription(hoveredStatusEffectInstance),
            StatusEffectUtil.getDurationText(hoveredStatusEffectInstance, 1.0F, client.world!!.tickManager.getTickRate())
        )
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
            if (wide) context.drawGuiTexture(RenderLayer::getGuiTextured, EFFECT_BACKGROUND_LARGE_TEXTURE, x, y, 120, 32)
            else context.drawGuiTexture(RenderLayer::getGuiTextured, EFFECT_BACKGROUND_SMALL_TEXTURE, x, y, 32, 32)

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
            context.drawSpriteStretched(RenderLayer::getGuiTextured, sprite, x + if (wide) 6 else 7, y + 7, 18, 18)

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
            context.drawTextWithShadow(parent.getTextRenderer(), descriptionText, x + 10 + 18, y + 6, 16777215)

            val durationText = StatusEffectUtil.getDurationText(it, 1.0F, client.world!!.tickManager.getTickRate())
            context.drawTextWithShadow(parent.getTextRenderer(), durationText, x + 10 + 18, y + 6 + 10, 8355711)

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