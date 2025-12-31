package de.fuballer.mcendgame.client.component.screen

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.logging.LogUtils
import de.fuballer.mcendgame.main.component.block.CustomBlocks
import de.fuballer.mcendgame.main.component.block.dungeon_device.DungeonDeviceScreenHandler
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.dungeon.enemy.EnemyLevelScalingSettings
import de.fuballer.mcendgame.main.component.dungeon.level.DungeonLevelSettings
import de.fuballer.mcendgame.main.messaging.misc.GetCustomAttributesTextsCommand
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import de.maucon.mauconframework.command.CommandGateway
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextWidget
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.model.UnbakedModel
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import kotlin.math.ceil
import kotlin.math.min

private val TEXTURE = IdentifierUtil.default("textures/gui/container/dungeon_device.png")
private val OPEN_DUNGEON_BUTTON_TEXT = Text.translatable("container.mcendgame.dungeon_device.open")
private val ENEMY_ATTRIBUTES_TEXT = Text.translatable("container.mcendgame.dungeon_device.enemy_attributes")
private val BOSS_ATTRIBUTES_TEXT = Text.translatable("container.mcendgame.dungeon_device.boss_attributes")

private val ATTRIBUTE_PANEL_TEXTURE = IdentifierUtil.default("textures/gui/container/dungeon_device_attribute_panel.png")
private const val ATTRIBUTE_PANEL_TEXTURE_EDGE_WIDTH = 4
private const val ATTRIBUTE_PANEL_MAX_WIDTH = 150
private const val ATTRIBUTE_HEADER_WIDGET_HEIGHT = 10
private const val ATTRIBUTE_TEXT_WIDGET_SCALE = 0.75f
private const val ATTRIBUTE_TEXT_WIDGET_HEIGHT = 10
private const val ATTRIBUTE_TEXT_WIDGET_Y_OFFSET = 10

private const val SHOW_ATTRIBUTES_BUTTON_WIDTH = 10
private const val SHOW_ATTRIBUTES_BUTTON_HEIGHT = 20
private val SHOW_ATTRIBUTES_BUTTON_TEXT = Text.literal(">")
private val HIDE_ATTRIBUTES_BUTTON_TEXT = Text.literal("<")

@Environment(EnvType.CLIENT)
class DungeonDeviceScreen(
    handler: DungeonDeviceScreenHandler,
    private val inventory: PlayerInventory,
    title: Text,
) : HandledScreen<DungeonDeviceScreenHandler>(handler, inventory, title) {
    private val log = LogUtils.getLogger()
    private var showLevelAttributes = false
    private var levelScalingTextWidgets = mutableListOf<TextWidget>()

    private val createDungeonButton = ButtonWidget
        .builder(OPEN_DUNGEON_BUTTON_TEXT, ::onCreateDungeonButtonPress)
        .size(36, 12)
        .build()

    override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2

        createDungeonButton.setPosition((width - backgroundWidth) / 2 + 70, (height - backgroundHeight) / 2 + 62)

        val playerDungeonLevel = handler.payload.playerDungeonLevel
        addDrawableChild(
            TextWidget(
                (width - backgroundWidth) / 2 + 8,
                (height - backgroundHeight) / 2 + 35,
                100,
                10,
                Text.translatable("text.mcendgame.dungeon.device.level", playerDungeonLevel.level),
                textRenderer
            ).alignLeft()
        )
        addDrawableChild(
            ScalableTextWidget(
                (width - backgroundWidth) / 2 + 8,
                (height - backgroundHeight) / 2 + 45,
                100,
                10,
                Text.translatable(
                    "text.mcendgame.dungeon.device.progress",
                    playerDungeonLevel.levelProgress,
                    DungeonLevelSettings.LEVEL_INCREASE_THRESHOLD
                ),
                textRenderer,
                0.75f
            ).alignLeft()
        )

        addDrawableChild(
            ButtonWidget.builder(SHOW_ATTRIBUTES_BUTTON_TEXT) { toggleButton ->
                showLevelAttributes = !showLevelAttributes
                toggleButton.x = getToggleButtonX()
                toggleButton.message = if (showLevelAttributes) HIDE_ATTRIBUTES_BUTTON_TEXT else SHOW_ATTRIBUTES_BUTTON_TEXT
            }.dimensions(
                getToggleButtonX(),
                (height - SHOW_ATTRIBUTES_BUTTON_HEIGHT) / 2,
                SHOW_ATTRIBUTES_BUTTON_WIDTH,
                SHOW_ATTRIBUTES_BUTTON_HEIGHT
            ).build()
        )

        addDrawableChild(createDungeonButton)

        initLevelScalingDetails(playerDungeonLevel.level)
    }

    private fun getToggleButtonX(): Int {
        val hiddenX = (width + backgroundWidth) / 2
        if (!showLevelAttributes) return hiddenX
        return min(width - SHOW_ATTRIBUTES_BUTTON_WIDTH - 5, hiddenX + ATTRIBUTE_PANEL_MAX_WIDTH)
    }

    private fun initLevelScalingDetails(dungeonLevel: Int) {
        levelScalingTextWidgets = mutableListOf<TextWidget>()

        val x = (width + backgroundWidth) / 2 + 6
        var y = (height - backgroundHeight) / 2 + 6
        val width = getLevelScalingTextWidgetWidth()

        val enemyAttributes = EnemyLevelScalingSettings.getEnemyLevelAttributes(dungeonLevel)
        if (enemyAttributes.isNotEmpty()) {
            y += initLevelScalingHeader(ENEMY_ATTRIBUTES_TEXT, x, y, width) + 3
            y += initLevelScalingAttributeTextsPart(enemyAttributes, x, y, width)
            y += 5
        }

        val bossAttributes = EnemyLevelScalingSettings.getBossLevelAttributes(dungeonLevel)
        if (bossAttributes.isNotEmpty()) {
            y += initLevelScalingHeader(BOSS_ATTRIBUTES_TEXT, x, y, width) + 3
            y += initLevelScalingAttributeTextsPart(bossAttributes, x, y, width)
            y += 5
        }
    }

    private fun getLevelScalingTextWidgetWidth(): Int {
        val xStart = (width + backgroundWidth) / 2 + 5
        val widgetWidth = ((width - SHOW_ATTRIBUTES_BUTTON_WIDTH - 10) - xStart)
        return min(widgetWidth, ATTRIBUTE_PANEL_MAX_WIDTH - 10)
    }

    private fun initLevelScalingHeader(
        text: Text,
        x: Int,
        y: Int,
        width: Int,
    ): Int {
        levelScalingTextWidgets.add(
            ScalableTextWidget(x, y, width, ATTRIBUTE_HEADER_WIDGET_HEIGHT, text, textRenderer, 1f)
                .alignLeft()
        )
        return ATTRIBUTE_HEADER_WIDGET_HEIGHT
    }

    private fun initLevelScalingAttributeTextsPart(
        attributes: List<CustomAttribute>,
        xStart: Int,
        yStart: Int,
        width: Int,
    ): Int {
        if (attributes.isEmpty()) return 0

        val attributeTextCommand = GetCustomAttributesTextsCommand(attributes)
        val cmd = CommandGateway.apply(attributeTextCommand)

        val widgetWidth = (width / ATTRIBUTE_TEXT_WIDGET_SCALE).toInt()

        for ((index, text) in cmd.texts.withIndex()) {
            levelScalingTextWidgets.add(
                ScalableTextWidget(
                    xStart,
                    yStart + index * ATTRIBUTE_TEXT_WIDGET_Y_OFFSET,
                    widgetWidth,
                    ATTRIBUTE_TEXT_WIDGET_HEIGHT,
                    text,
                    textRenderer,
                    ATTRIBUTE_TEXT_WIDGET_SCALE
                ).alignLeft()
            )
        }

        return (attributes.size - 1) * ATTRIBUTE_TEXT_WIDGET_Y_OFFSET + ceil(ATTRIBUTE_TEXT_WIDGET_HEIGHT * ATTRIBUTE_TEXT_WIDGET_SCALE).toInt()
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        if (showLevelAttributes) renderAttributesPanel(context, mouseX, mouseY, delta)
        drawMouseoverTooltip(context, mouseX, mouseY)
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        val textureX = (width - backgroundWidth) / 2
        val textureY = (height - backgroundHeight) / 2

        context.drawItem(CustomBlocks.DUNGEON_DEVICE.asItem().defaultStack, textureX + 8, textureY + 8)

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

    private fun renderAttributesPanel(
        context: DrawContext,
        mouseX: Int,
        mouseY: Int,
        delta: Float,
    ) {
        val x1 = (width + backgroundWidth) / 2
        val y1 = (height - backgroundHeight) / 2
        var x2 = width - SHOW_ATTRIBUTES_BUTTON_WIDTH - 5

        val w = min(x2 - x1, ATTRIBUTE_PANEL_MAX_WIDTH)
        x2 = x1 + w

        context.drawTexture(
            { texture: Identifier -> RenderLayer.getGuiTextured(texture) },
            ATTRIBUTE_PANEL_TEXTURE,
            x1,
            y1,
            0f,
            0f,
            ATTRIBUTE_PANEL_TEXTURE_EDGE_WIDTH,
            backgroundHeight,
            256,
            256
        )

        context.drawTexture(
            { texture: Identifier -> RenderLayer.getGuiTextured(texture) },
            ATTRIBUTE_PANEL_TEXTURE,
            x1 + ATTRIBUTE_PANEL_TEXTURE_EDGE_WIDTH,
            y1,
            ATTRIBUTE_PANEL_TEXTURE_EDGE_WIDTH.toFloat(),
            0f,
            w - 2 * ATTRIBUTE_PANEL_TEXTURE_EDGE_WIDTH,
            backgroundHeight,
            256,
            256
        )

        context.drawTexture(
            { texture: Identifier -> RenderLayer.getGuiTextured(texture) },
            ATTRIBUTE_PANEL_TEXTURE,
            x2 - ATTRIBUTE_PANEL_TEXTURE_EDGE_WIDTH,
            y1,
            176f - ATTRIBUTE_PANEL_TEXTURE_EDGE_WIDTH,
            0f,
            ATTRIBUTE_PANEL_TEXTURE_EDGE_WIDTH,
            backgroundHeight,
            256,
            256
        )

        levelScalingTextWidgets.forEach { it.render(context, mouseX, mouseY, delta) }
    }

    private fun onCreateDungeonButtonPress(button: ButtonWidget) {
        ClientPlayNetworking.send(handler.payload)
        close()
        log.info("Dungeon opened by ${inventory.player.gameProfile.name}")
    }
}