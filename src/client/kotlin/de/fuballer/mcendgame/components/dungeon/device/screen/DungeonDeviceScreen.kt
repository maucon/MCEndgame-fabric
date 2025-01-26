package de.fuballer.mcendgame.components.dungeon.device.screen

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class DungeonDeviceScreen(handler: DungeonDeviceScreenHandler, inventory: PlayerInventory, title: Text) :
    HandledScreen<DungeonDeviceScreenHandler>(handler, inventory, title) {

    override fun init() {
        super.init()
        titleX = (this.backgroundWidth - textRenderer.getWidth(this.title)) / 2
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        this.drawMouseoverTooltip(context, mouseX, mouseY)
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        val i = (this.width - this.backgroundWidth) / 2
        val j = (this.height - this.backgroundHeight) / 2

        context.drawTexture(
            { texture: Identifier -> RenderLayer.getGuiTextured(texture) }, TEXTURE, i, j, 0.0f, 0.0f,
            this.backgroundWidth,
            this.backgroundHeight, 256, 256
        )
        context.drawBorder(10, 10, 100, 100, -0x1)

        context.drawText(textRenderer, "CROSSE CRARA", 40, 40 - textRenderer.fontHeight - 10, -0x1, true)
    }

    companion object {
        private val TEXTURE: Identifier = Identifier.ofVanilla("textures/gui/container/dispenser.png")
    }
}
