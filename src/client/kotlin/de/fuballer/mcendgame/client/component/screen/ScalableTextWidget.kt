package de.fuballer.mcendgame.client.component.screen

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.TextWidget
import net.minecraft.text.Text

class ScalableTextWidget(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    text: Text,
    textRenderer: TextRenderer,
    private val scale: Float,
) : TextWidget(x, y, width, height, text, textRenderer) {
    override fun renderWidget(context: DrawContext, mouseX: Int, mouseY: Int, deltaTicks: Float) {
        val matrices = context.matrices

        matrices.pushMatrix()
        matrices.translate(x * (1 - scale), y * (1 - scale))
        matrices.scale(scale, scale)

        super.renderWidget(context, mouseX, mouseY, deltaTicks)

        matrices.popMatrix()
    }
}