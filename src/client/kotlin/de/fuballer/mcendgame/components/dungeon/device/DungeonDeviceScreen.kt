package de.fuballer.mcendgame.components.dungeon.device

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.toast.SystemToast
import net.minecraft.text.Text

class DungeonDeviceScreen(
    title: Text
) : Screen(title) {
    override fun init() {
        val buttonWidget: ButtonWidget = ButtonWidget.builder(Text.of("Hello World")) { btn ->
            // When the button is clicked, we can display a toast to the screen.
            this.client!!.toastManager.add(
                SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.of("Hello World!"), Text.of("This is a toast."))
            )
        }.dimensions(40, 40, 120, 20).build()

        this.addDrawableChild(buttonWidget)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // textRenderer, text, x, y, color, hasShadow
        context.drawText(this.textRenderer, "Special Button", 40, 40 - this.textRenderer.fontHeight - 10, -0x1, true)
    }
}