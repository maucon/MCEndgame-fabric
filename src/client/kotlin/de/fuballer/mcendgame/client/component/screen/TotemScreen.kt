package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.totem.TotemScreenHandler
import de.fuballer.mcendgame.main.component.totem.TotemSlot
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text
import net.minecraft.util.Identifier

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
            { texture: Identifier -> RenderLayer.getGuiTextured(texture) },
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

    override fun drawSlot(context: DrawContext, slot: Slot) {
        super.drawSlot(context, slot)

        val totemSlot = slot as? TotemSlot ?: return
        val color = totemSlot.type.color.intColor

        //top
        context.drawHorizontalLine(slot.x - 2, slot.x + 17, slot.y - 2, color)
        //bottom
        context.drawHorizontalLine(slot.x - 2, slot.x + 17, slot.y + 17, color)
        //left
        context.drawVerticalLine(slot.x - 2, slot.y - 2, slot.y + 17, color)
        //right
        context.drawVerticalLine(slot.x + 17, slot.y - 2, slot.y + 17, color)
    }
}