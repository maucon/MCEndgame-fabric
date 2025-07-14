package de.fuballer.mcendgame.main.messaging.dungeon

import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorld
import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos

/**
 * only server-side
 */
data class DungeonGeneratedEvent(
    val originWorld: ServerWorld,
    val dungeonWorld: DungeonWorld,
    val startPos: SpawnPosition,
    val dungeonDevicePos: BlockPos
)