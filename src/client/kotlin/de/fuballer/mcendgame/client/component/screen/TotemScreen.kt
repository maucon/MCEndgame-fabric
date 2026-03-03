package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.totem.TotemScreenHandler
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.gl.RenderPipelines
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

private val TEXTURE = IdentifierUtil.default("textures/gui/container/totem.png")

class TotemScreen(
    handler: TotemScreenHandler,
    inventory: PlayerInventory,
    title: Text,
) : HandledScreen<TotemScreenHandler>(handler, inventory, title) {
    init {
        backgroundWidth = 176
        backgroundHeight = 169
        playerInventoryTitleY = backgroundHeight - 94
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, deltaTicks: Float) {
        super.render(context, mouseX, mouseY, deltaTicks)
        drawMouseoverTooltip(context, mouseX, mouseY)
    }

    override fun drawBackground(
        context: DrawContext,
        deltaTicks: Float,
        mouseX: Int,
        mouseY: Int,
    ) {
        val textureX = (width - backgroundWidth) / 2
        val textureY = (height - backgroundHeight) / 2

        context.drawTexture(
            RenderPipelines.GUI_TEXTURED,
            TEXTURE,
            textureX,
            textureY,
            0.0f,
            0.0f,
            backgroundWidth,
            backgroundHeight,
            256,
            256,
        )
    }
}