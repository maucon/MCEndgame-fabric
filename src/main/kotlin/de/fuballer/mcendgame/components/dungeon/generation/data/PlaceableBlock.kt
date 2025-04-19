package de.fuballer.mcendgame.components.dungeon.generation.data

import de.fuballer.mcendgame.util.Vec3iExtension.rotateY90
import de.fuballer.mcendgame.util.Vec3iExtension.toBlockPos
import net.minecraft.block.Block
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3i

data class PlaceableBlock(
    val x: Int,
    val y: Int,
    val z: Int,
    val rotation16: Int,
    val block: Block,
) {
    fun getBlockPos(
        offset: Vec3i = Vec3i(0, 0, 0),
        rotation90: Int = 0,
    ): BlockPos {
        val rotatedPos = Vec3i(x, y, z).rotateY90(rotation90)
        val summedPos = rotatedPos.add(offset)
        return summedPos.toBlockPos()
    }
}