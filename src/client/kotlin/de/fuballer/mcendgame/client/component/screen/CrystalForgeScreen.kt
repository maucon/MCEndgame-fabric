package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.block.blocks.crystalforge.CrystalForgeScreenHandler
import de.fuballer.mcendgame.main.component.block.blocks.crystalforge.CrystalForgeSettings
import de.fuballer.mcendgame.main.component.block.blocks.crystalforge.network.CrystalForgePayload
import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import de.fuballer.mcendgame.main.util.ColorUtil
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.gl.RenderPipelines
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextWidget
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text
import java.awt.Color
import kotlin.math.pow
import kotlin.math.sin

private val TEXTURE = IdentifierUtil.default("textures/gui/container/crystal_forge.png")
private val FORGE_BUTTON_TEXT = Text.translatable("${CrystalForgeSettings.CONTAINER_BASE_KEY}forge_button")

private const val FORGE_ANIMATION_DURATION = 10.0

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

    private var forgeAnimationTime = -1F
    private var forgeAnimationColor = Color.WHITE
    private var forgeAnimationX1 = 0
    private var forgeAnimationY1 = 0
    private var forgeAnimationX2 = 0
    private var forgeAnimationY2 = 0

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

        val toForgeSlot: Slot = handler.slots[0]
        val toForgeSlotX = (width - backgroundWidth) / 2 + toForgeSlot.x
        val toForgeSlotY = (height - backgroundHeight) / 2 + toForgeSlot.y
        forgeAnimationX1 = toForgeSlotX - 2
        forgeAnimationY1 = toForgeSlotY - 2
        forgeAnimationX2 = toForgeSlotX + 18
        forgeAnimationY2 = toForgeSlotY + 18
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

        drawForgeAnimation(context, deltaTicks)
    }

    private fun drawForgeAnimation(
        context: DrawContext,
        deltaTicks: Float,
    ) {
        if (forgeAnimationTime >= 0) {
            val progress = (forgeAnimationTime / FORGE_ANIMATION_DURATION).coerceIn(0.0, 1.0)
            val pulse = sin(progress.pow(0.4) * Math.PI)

            val bgAlpha = (pulse * 100).toInt()
            val bgColor = ColorUtil.rgbaToInt(forgeAnimationColor.red, forgeAnimationColor.green, forgeAnimationColor.blue, bgAlpha)
            context.fill(forgeAnimationX1 + 1, forgeAnimationY1 + 1, forgeAnimationX2 - 1, forgeAnimationY2 - 1, bgColor)

            val outlineAlpha = (pulse * 255).toInt()
            val outlineColor = ColorUtil.rgbaToInt(forgeAnimationColor.red, forgeAnimationColor.green, forgeAnimationColor.blue, outlineAlpha)
            context.fill(forgeAnimationX1, forgeAnimationY1, forgeAnimationX2, forgeAnimationY1 + 1, outlineColor)
            context.fill(forgeAnimationX1, forgeAnimationY2 - 1, forgeAnimationX2, forgeAnimationY2, outlineColor)
            context.fill(forgeAnimationX1, forgeAnimationY1 + 1, forgeAnimationX1 + 1, forgeAnimationY2 - 1, outlineColor)
            context.fill(forgeAnimationX2 - 1, forgeAnimationY1 + 1, forgeAnimationX2, forgeAnimationY2 - 1, outlineColor)

            forgeAnimationTime += deltaTicks
            if (forgeAnimationTime >= FORGE_ANIMATION_DURATION) forgeAnimationTime = -1F
        }
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

            forgeAnimationTime = 0F
            forgeAnimationColor = crystalItem.forgeColor
        }
    }
}