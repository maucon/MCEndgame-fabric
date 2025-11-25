package de.fuballer.mcendgame.main.component.dungeon.world

import net.minecraft.server.world.ServerWorld

/**
 * only server-side
 */
data class DungeonWorldClosedEvent(
    val dungeonWorld: ServerWorld,
)