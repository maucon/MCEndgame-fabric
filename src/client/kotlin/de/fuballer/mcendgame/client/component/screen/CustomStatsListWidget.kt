package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.stats.CustomStatsRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget
import net.minecraft.screen.ScreenTexts
import net.minecraft.stat.Stat
import net.minecraft.stat.StatFormatter
import net.minecraft.stat.Stats
import net.minecraft.text.Text
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class CustomStatsListWidget(
    client: MinecraftClient,
    width: Int,
    contentHeight: Int,
) : AlwaysSelectedEntryListWidget<CustomStatsListWidget.Entry>(
    client, width, contentHeight, 33, 14
) {
    init {
        val statHandler = client.player?.statHandler
        if (statHandler != null) {
            for (id in CustomStatsRegistry.ENTRIES) {
                val stat = Stats.CUSTOM.getOrCreateStat(id, StatFormatter.DEFAULT)
                addEntry(Entry(stat, statHandler.getStat(stat)))
            }
        }
    }

    override fun getRowWidth(): Int = 280

    override fun drawMenuListBackground(context: DrawContext) {}

    override fun drawHeaderAndFooterSeparators(context: DrawContext) {}

    @Environment(EnvType.CLIENT)
    inner class Entry(
        private val stat: Stat<Identifier>,
        private val value: Int,
    ) : AlwaysSelectedEntryListWidget.Entry<Entry>() {

        private val displayName: Text = Text.translatable(
            "stat.${stat.value.namespace}.${stat.value.path}"
        )

        private fun getFormatted(): String = stat.format(value)

        override fun render(
            context: DrawContext,
            mouseX: Int,
            mouseY: Int,
            hovered: Boolean,
            deltaTicks: Float,
        ) {
            val textRenderer = client.textRenderer
            val y = contentMiddleY - textRenderer.fontHeight / 2
            val index = this@CustomStatsListWidget.children().indexOf(this)
            val color = if (index % 2 == 0) -1 else -4539718 // white / gray alternating

            // Draw stat name on the left
            context.drawTextWithShadow(textRenderer, displayName, contentX + 2, y, color)

            // Draw stat value on the right
            val formatted = getFormatted()
            context.drawTextWithShadow(
                textRenderer,
                formatted,
                contentRightEnd - textRenderer.getWidth(formatted) - 4,
                y,
                color,
            )
        }

        override fun getNarration(): Text = Text.translatable(
            "narrator.select",
            Text.empty()
                .append(displayName)
                .append(ScreenTexts.SPACE)
                .append(getFormatted()),
        )
    }
}