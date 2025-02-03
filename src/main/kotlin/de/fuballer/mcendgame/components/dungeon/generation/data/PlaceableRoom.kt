package de.fuballer.mcendgame.components.dungeon.generation.data

import net.minecraft.util.math.Vec3i

data class PlaceableRoom(
    val type: RoomType,
    val position: Vec3i,
    val rotation: Double,
    //val extraBlocks: List<PlaceableBlock> = listOf()
)
