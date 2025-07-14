package de.fuballer.mcendgame.main.component.dungeon.world

import de.fuballer.mcendgame.main.accessor.DungeonWorldAccessor
import net.minecraft.server.world.ServerWorld

data class DungeonWorld(
    val world: ServerWorld,
    val dungeon: DungeonWorldAccessor = world as DungeonWorldAccessor,
)