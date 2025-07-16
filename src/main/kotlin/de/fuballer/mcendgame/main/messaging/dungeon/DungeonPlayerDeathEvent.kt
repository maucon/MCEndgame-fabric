package de.fuballer.mcendgame.main.messaging.dungeon

import de.fuballer.mcendgame.main.component.dungeon.player.DungeonPlayerEntity
import net.minecraft.entity.LivingEntity

data class DungeonPlayerDeathEvent(
    val isClient: Boolean,
    val dungeonPlayer: DungeonPlayerEntity,
    val killer: LivingEntity?
)