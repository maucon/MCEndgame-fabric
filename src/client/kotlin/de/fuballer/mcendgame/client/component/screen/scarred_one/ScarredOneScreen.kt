package de.fuballer.mcendgame.client.component.screen.scarred_one

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.scarred_one.data.RolledScarredOneEffect
import de.fuballer.mcendgame.main.util.ColorUtil
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text

class ScarredOneScreen(
    val positiveEffects: List<RolledScarredOneEffect>,
    val negativeEffects: List<RolledScarredOneEffect>,
) : Screen(Text.empty()) {
    override fun init() {
        super.init()

        val centerX = width / 2
        val centerY = height / 2

        addDrawableChild(
            ButtonWidget.builder(Text.literal("Accept")) {
                println("Accepted")
            }.dimensions(centerX - 100, centerY + 20, 200, 20)
                .build()
        )

        addDrawableChild(
            ButtonWidget.builder(Text.literal("Decline")) {
                println("Declined")
            }.dimensions(centerX - 100, centerY + 45, 200, 20)
                .build()
        )
    }

    override fun render(
        context: DrawContext,
        mouseX: Int,
        mouseY: Int,
        delta: Float
    ) {
        super.render(context, mouseX, mouseY, delta)

        val text = positiveEffects[0].getText()
        val textWidth = textRenderer.getWidth(text.string)
        context.drawText(
            textRenderer,
            text,
            (width / 2) - (textWidth / 2),
            50,
            ColorUtil.rgbaToInt(0, 255, 0, 255),
            false
        )

    }

    override fun shouldPause(): Boolean = false
}