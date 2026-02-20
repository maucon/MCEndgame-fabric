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
        val stack = cmd.itemStack
        if (!stack.isCorrupted()) return

        val texts = cmd.texts
        if (cmd.tooltipType.isAdvanced) {
            val hasDurabilityLine = stack.damage > 0
            val offset = if (hasDurabilityLine) 3 else 2
            texts.add(texts.size - offset, CORRUPTION_TEXT)
        } else {
            texts.add(CORRUPTION_TEXT)
        }
    }
}