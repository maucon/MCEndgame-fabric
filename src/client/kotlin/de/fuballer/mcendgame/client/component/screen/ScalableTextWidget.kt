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
        matrices.push()
        matrices.translate(x.toDouble() * (1 - scale), y.toDouble() * (1 - scale), 0.0)
        matrices.scale(scale, scale, 1f)

        super.renderWidget(context, mouseX, mouseY, deltaTicks)

        matrices.pop()
    }
}