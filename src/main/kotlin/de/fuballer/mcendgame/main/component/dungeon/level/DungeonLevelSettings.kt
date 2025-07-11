package de.fuballer.mcendgame.main.component.dungeon.level

import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting

object DungeonLevelSettings {
    const val LEVEL_INCREASE_THRESHOLD = 3

    private val DUNGEON_COMPLETED_PREFIX: MutableText = Text.translatable("text.mcendgame.dungeon_level.dungeon_completed")
        .formatted(Formatting.GOLD, Formatting.BOLD)

    val NO_PROGRESS_MESSAGE: Text = DUNGEON_COMPLETED_PREFIX.copy().append(
        Text.translatable("text.mcendgame.dungeon_level.no_progress")
            .styled { style -> style.withBold(false).withColor(Formatting.RED) }
    )

    fun getRegressMessage(level: Int, progress: Int): Text =
        Text.translatable("text.mcendgame.dungeon_level.dungeon_death").formatted(Formatting.RED, Formatting.BOLD)
            .append(
                Text.translatable("text.mcendgame.dungeon_level.regress", level, progress, LEVEL_INCREASE_THRESHOLD)
                    .styled { style -> style.withBold(false).withColor(Formatting.AQUA) }
            )

    fun getProgressMessage(level: Int, progress: Int): Text = DUNGEON_COMPLETED_PREFIX.copy().append(
        Text.translatable("text.mcendgame.dungeon_level.progress", level, progress, LEVEL_INCREASE_THRESHOLD)
            .styled { style -> style.withBold(false).withColor(Formatting.AQUA) }
    )
}