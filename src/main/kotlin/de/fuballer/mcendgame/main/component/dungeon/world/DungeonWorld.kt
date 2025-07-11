package de.fuballer.mcendgame.main.component.dungeon.world

import de.fuballer.mcendgame.main.accessor.ServerWorldDungeonWorldAccessor
import net.minecraft.server.world.ServerWorld

data class DungeonWorld(
    val world: ServerWorld,
    val dungeon: ServerWorldDungeonWorldAccessor = world as ServerWorldDungeonWorldAccessor,
)