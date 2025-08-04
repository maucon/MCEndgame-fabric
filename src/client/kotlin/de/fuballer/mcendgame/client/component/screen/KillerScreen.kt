package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.entity.custom.CustomEntities
import de.fuballer.mcendgame.main.component.entity.custom.entities.bonecrusher.BonecrusherEntity
import de.fuballer.mcendgame.main.component.killer.KillerScreenHandler
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

private val TEXTURE = IdentifierUtil.default("textures/gui/container/dungeon_device.png") //TODO add own texture

class KillerScreen(
    handler: KillerScreenHandler,
    inventory: PlayerInventory,
    title: Text,
) : HandledScreen<KillerScreenHandler>(handler, inventory, title) {
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
            256,
            256
        )

        val player = client?.player ?: return
        val killer = BonecrusherEntity(CustomEntities.BONECRUSHER, player.world) //TODO get actual killer
        InventoryScreen.drawEntity(context, x + 26, y + 8, x + 75, y + 78, 30, 0.0625F, mouseX.toFloat(), mouseY.toFloat(), player)
    }
}