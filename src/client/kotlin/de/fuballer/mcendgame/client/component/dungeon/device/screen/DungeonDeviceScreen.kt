package de.fuballer.mcendgame.client.component.dungeon.device.screen

import com.mojang.logging.LogUtils
import de.fuballer.mcendgame.main.component.dungeon.device.DungeonDevice
import de.fuballer.mcendgame.main.component.dungeon.device.screen.DungeonDeviceScreenHandler
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

private val TEXTURE = IdentifierUtil.default("textures/gui/container/dungeon_device.png")
private val OPEN_DUNGEON_BUTTON_TEXT = Text.translatable("container.mcendgame.dungeon_device.open")

@Environment(EnvType.CLIENT)
class DungeonDeviceScreen(
    handler: DungeonDeviceScreenHandler,
    private val inventory: PlayerInventory,
    title: Text
) : HandledScreen<DungeonDeviceScreenHandler>(handler, inventory, title) {
    private val log = LogUtils.getLogger()

    private val createDungeonButton = ButtonWidget
        .builder(OPEN_DUNGEON_BUTTON_TEXT, ::onCreateDungeonButtonPress)
        .size(36, 12)
        .build()

    override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2

        createDungeonButton.setPosition((width - backgroundWidth) / 2 + 69, (height - backgroundHeight) / 2 + 62)
        addDrawableChild(createDungeonButton)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        drawMouseoverTooltip(context, mouseX, mouseY)
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        val textureX = (width - backgroundWidth) / 2
        val textureY = (height - backgroundHeight) / 2

        context.drawItem(DungeonDevice.BLOCK.asItem().defaultStack, textureX + 8, textureY + 8)

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
    }

    private fun onCreateDungeonButtonPress(button: ButtonWidget) {
        ClientPlayNetworking.send(handler.payload)
        close()
        log.info("Dungeon opened by ${inventory.player.gameProfile.name}")
    }
}