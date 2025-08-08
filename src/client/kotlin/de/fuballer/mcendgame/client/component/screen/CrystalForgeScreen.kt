package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeScreenHandler
import de.fuballer.mcendgame.main.component.block.crystalforge.network.CrystalForgePayload
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

private val TEXTURE = IdentifierUtil.default("textures/gui/container/crystal_forge.png")
private val FORGE_BUTTON_TEXT = Text.translatable("container.mcendgame.crystal_forge.forge")

class CrystalForgeScreen(
    handler: CrystalForgeScreenHandler,
    inventory: PlayerInventory,
    title: Text,
) : HandledScreen<CrystalForgeScreenHandler>(handler, inventory, title) {
    private val forgeButton = ButtonWidget
        .builder(FORGE_BUTTON_TEXT, ::onForgeButtonPress)
        .size(36, 12)
        .build()

    override fun init() {
        super.init()

        forgeButton.setPosition((width - backgroundWidth) / 2 + 70, (height - backgroundHeight) / 2 + 62)
        addDrawableChild(forgeButton)
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
            backgroundWidth,
            backgroundHeight,
        )
    }

    private fun onForgeButtonPress(button: ButtonWidget) {
        ClientPlayNetworking.send(CrystalForgePayload())
    }
}