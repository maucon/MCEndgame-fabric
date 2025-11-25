package de.fuballer.mcendgame.main.component.dungeon.world

import net.minecraft.server.world.ServerWorld

data class DungeonWorldClosedEvent(
    val dungeonWorld: ServerWorld,
)