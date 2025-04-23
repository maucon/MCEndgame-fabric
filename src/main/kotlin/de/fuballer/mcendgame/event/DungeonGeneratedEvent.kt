package de.fuballer.mcendgame.event

import de.fuballer.mcendgame.components.dungeon.generation.data.SpawnPosition
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import java.util.*

/**
 * only server-side
 */
data class DungeonGeneratedEvent(
    val originWorld: ServerWorld,
    val dungeonWorld: ServerWorld,
    val startPos: SpawnPosition,
    val dungeonDevicePos: BlockPos
)