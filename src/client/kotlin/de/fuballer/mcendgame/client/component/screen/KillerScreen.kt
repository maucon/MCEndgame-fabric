package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.killer.KillerScreenHandler
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

private val TEXTURE = IdentifierUtil.default("textures/gui/container/killer.png")
private const val ENTITY_DRAW_PANEL_X = 26
private const val ENTITY_DRAW_PANEL_WIDTH = 77
private const val ENTITY_DRAW_PANEL_Y = 18
private const val ENTITY_DRAW_PANEL_HEIGHT = 110
private const val ENTITY_DRAW_PANEL_RATIO = ENTITY_DRAW_PANEL_WIDTH / ENTITY_DRAW_PANEL_HEIGHT.toDouble()
private const val ENTITY_BASE_SIZE = 75

class KillerScreen(
    handler: KillerScreenHandler,
    inventory: PlayerInventory,
    title: Text,
) : HandledScreen<KillerScreenHandler>(handler, inventory, title) {
    val statusEffectsDisplay = CustomStatusEffectsDisplay(this)

    init {
        backgroundWidth = 111
        backgroundHeight = 136

        statusEffectsDisplay.backgroundHeight = 24
        statusEffectsDisplay.smallWidth = 24
        statusEffectsDisplay.yOffsetPerEffect = { effectCount -> if (effectCount <= 4) 25 else (backgroundHeight - statusEffectsDisplay.backgroundHeight) / (effectCount - 1) }
        statusEffectsDisplay.spriteXOffset = { 3 }
        statusEffectsDisplay.spriteYOffset = 3
        statusEffectsDisplay.descriptionTextYOffset = 8
        statusEffectsDisplay.renderDurationText = false
        statusEffectsDisplay.isWide = { false }
    }

    override fun render(
        context: DrawContext,
        mouseX: Int,
        mouseY: Int,
        deltaTicks: Float
    ) {
        super.render(context, mouseX, mouseY, deltaTicks)
        val effects = handler.killer?.statusEffects ?: listOf<StatusEffectInstance>()
        statusEffectsDisplay.drawStatusEffects(
            context,
            x + backgroundWidth + 1,
            y,
            mouseX,
            mouseY,
            effects,
        )
        drawMouseoverTooltip(context, mouseX, mouseY)
    }

    override fun drawBackground(
        context: DrawContext,
        deltaTicks: Float,
        mouseX: Int,
        mouseY: Int
    ) {
        val textureX = (width - backgroundWidth) / 2
        val textureY = (height - backgroundHeight) / 2

        context.drawTexture(
            { texture: Identifier -> RenderLayer.getGuiTextured(texture) },
            TEXTURE,
            textureX,
            textureY,
            0.0f,
            0.0f,
            backgroundWidth,
            backgroundHeight,
            backgroundWidth,
            backgroundHeight,
        )

        drawKillerEntity(context, mouseX, mouseY)
    }

    private fun drawKillerEntity(
        context: DrawContext,
        mouseX: Int,
        mouseY: Int,
    ) {
        val killer = handler.killer ?: return
        val killerRatio = killer.width / killer.height

        val sizeFactor = 1.0 / if (killerRatio > ENTITY_DRAW_PANEL_RATIO) killer.width / ENTITY_DRAW_PANEL_RATIO.toFloat() else killer.height
        val size = (ENTITY_BASE_SIZE * sizeFactor).toInt()

        InventoryScreen.drawEntity(
            context,
            x + ENTITY_DRAW_PANEL_X,
            y + ENTITY_DRAW_PANEL_Y,
            x + ENTITY_DRAW_PANEL_X + ENTITY_DRAW_PANEL_WIDTH,
            y + ENTITY_DRAW_PANEL_Y + ENTITY_DRAW_PANEL_HEIGHT,
            size,
            0.0625F,
            mouseX.toFloat(),
            mouseY.toFloat(),
            killer
        )
    }

    override fun drawForeground(
        context: DrawContext,
        mouseX: Int,
        mouseY: Int
    ) {
        context.drawText(textRenderer, title, titleX, titleY, 4210752, false)
    }
}