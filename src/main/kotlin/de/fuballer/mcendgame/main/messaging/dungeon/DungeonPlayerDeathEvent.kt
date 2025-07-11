package de.fuballer.mcendgame.main.messaging.dungeon

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity

data class DungeonPlayerDeathEvent(
    val isClient: Boolean,
    val player: PlayerEntity,
    val killer: LivingEntity?
)