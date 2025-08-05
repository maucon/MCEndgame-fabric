package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.killer.KillerScreenHandler
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

private val TEXTURE = IdentifierUtil.default("textures/gui/container/killer.png")

class KillerScreen(
    handler: KillerScreenHandler,
    inventory: PlayerInventory,
    title: Text,
) : HandledScreen<KillerScreenHandler>(handler, inventory, title) {
    val statusEffectsDisplay = CustomStatusEffectsDisplay(this)
    var entityDrawPanelRatio = 1.0

    init {
        backgroundWidth = 111
        backgroundHeight = 126
        entityDrawPanelRatio = backgroundWidth / backgroundHeight.toDouble()

        statusEffectsDisplay.backgroundHeight = 24
        statusEffectsDisplay.yOffsetPerEffect = { effectCount -> if (effectCount <= 4) 25 else (backgroundHeight - statusEffectsDisplay.backgroundHeight) / (effectCount - 1) }
        statusEffectsDisplay.spriteYOffset = 3
        statusEffectsDisplay.descriptionTextYOffset = 8
        statusEffectsDisplay.renderDurationText = false
    }

    override fun render(
        context: DrawContext,
        mouseX: Int,
        mouseY: Int,
        deltaTicks: Float
    ) {
        super.render(context, mouseX, mouseY, deltaTicks)
        statusEffectsDisplay.drawStatusEffects(
            context,
            x + backgroundWidth + 1,
            y,
            mouseX,
            mouseY,
            listOf(
                StatusEffectInstance(StatusEffects.STRENGTH),
                StatusEffectInstance(StatusEffects.SPEED, 200, 3),
                StatusEffectInstance(StatusEffects.RESISTANCE, StatusEffectInstance.INFINITE, 100),
            )
        )
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
        val sizeFactor = 1.0 / if (killerRatio > entityDrawPanelRatio) killer.width else killer.height
        val size = (75 * sizeFactor).toInt()

        InventoryScreen.drawEntity(
            context,
            x + 26,
            y + 8,
            x + 103,
            y + 118,
            size,
            0.0625F,
            mouseX.toFloat(),
            mouseY.toFloat(),
            killer
        )
    }

    override fun drawForeground(context: DrawContext, mouseX: Int, mouseY: Int) {}
}