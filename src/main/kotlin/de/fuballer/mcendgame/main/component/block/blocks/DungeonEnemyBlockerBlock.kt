package de.fuballer.mcendgame.main.component.block.blocks

import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.isDungeonEnemyOrProjectile
import net.minecraft.block.BarrierBlock
import net.minecraft.block.BlockState
import net.minecraft.block.EntityShapeContext
import net.minecraft.block.ShapeContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.GameMode

class DungeonEnemyBlockerBlock(
    settings: Settings,
) : BarrierBlock(settings) {
    companion object {
        const val ID = "dungeon_enemy_blocker"
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext,
    ): VoxelShape {
        val entityContext = context as? EntityShapeContext ?: return VoxelShapes.empty()
        if (entityContext.entity?.isDungeonEnemyOrProjectile() != true) return VoxelShapes.empty()
        return VoxelShapes.fullCube()
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val entityContext = context as? EntityShapeContext ?: return VoxelShapes.empty()
        val player = entityContext.entity as? PlayerEntity ?: return VoxelShapes.empty()
        if (player.gameMode != GameMode.CREATIVE) return VoxelShapes.empty()
        return VoxelShapes.fullCube()
    }
}