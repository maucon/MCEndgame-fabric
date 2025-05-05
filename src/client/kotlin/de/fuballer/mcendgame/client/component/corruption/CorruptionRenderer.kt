package de.fuballer.mcendgame.client.component.corruption

import de.fuballer.mcendgame.client.messaging.RenderItemTooltipCommand
import de.fuballer.mcendgame.main.component.corruption.CorruptionExtensions.isCorrupted
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.text.Text
import net.minecraft.util.Formatting

private val CORRUPTION_TEXT = Text.translatable("mcendgame.corrupted").formatted(Formatting.DARK_RED)

@Injectable
@Environment(EnvType.CLIENT)
class CorruptionRenderer {
    @CommandHandler
    fun on(cmd: RenderItemTooltipCommand) {
        if (!cmd.itemStack.isCorrupted()) return

        val texts = cmd.texts
        if (cmd.tooltipType.isAdvanced) {
            texts.add(texts.size - 2, CORRUPTION_TEXT)
        } else {
            texts.add(CORRUPTION_TEXT)
        }
    }
}