package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeScreenHandler
import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeSettings
import de.fuballer.mcendgame.main.component.block.crystalforge.network.CrystalForgePayload
import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.gl.RenderPipelines
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextWidget
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

private val TEXTURE = IdentifierUtil.default("textures/gui/container/crystal_forge.png")
private val FORGE_BUTTON_TEXT = Text.translatable("${CrystalForgeSettings.CONTAINER_BASE_KEY}forge_button")

class CrystalForgeScreen(
    handler: CrystalForgeScreenHandler,
    inventory: PlayerInventory,
    title: Text,
) : HandledScreen<CrystalForgeScreenHandler>(handler, inventory, title) {
    private val forgeButton = ButtonWidget
        .builder(FORGE_BUTTON_TEXT, ::onForgeButtonPress)
        .size(36, 12)
        .build()

    private lateinit var forgeErrorText: TextWidget

    override fun init() {
        super.init()

        val backgroundX = (width - backgroundWidth) / 2
        val backgroundY = (height - backgroundHeight) / 2

        forgeButton.setPosition(backgroundX + 70, backgroundY + 62)
        addDrawableChild(forgeButton)

        forgeErrorText = TextWidget(
            backgroundX + 3,
            backgroundY - 10,
            200,
            10,
            Text.empty(),
            textRenderer
        )
        addDrawableChild(forgeErrorText)
    }

    override fun render(
        context: DrawContext,
        mouseX: Int,
        mouseY: Int,
        deltaTicks: Float
    ) {
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
            backgroundWidth,
            backgroundHeight,
        )
    }

    private fun onForgeButtonPress(button: ButtonWidget) {
        val toForgeStack = handler.slots[0].stack
        val crystalStack = handler.slots[1].stack

        val crystalItem = crystalStack.item
        if (crystalItem !is CrystalItem) {
            forgeErrorText.message = CrystalForgeSettings.getForgeErrorText("no_crystal")
            return
        }

        val cannotForgeReason = crystalItem.canForge(toForgeStack)
        if (cannotForgeReason != null) {
            forgeErrorText.message = cannotForgeReason
        } else {
            forgeErrorText.message = Text.empty()
            ClientPlayNetworking.send(CrystalForgePayload())
        }
    }
}