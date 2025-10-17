package de.fuballer.mcendgame.main.component.block.totem_statue

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

class TotemStatueBlock(
    settings: Settings
) : BlockWithEntity(settings) {

    companion object {
        const val ID = "totem_statue"

        private val SHAPE = createColumnShape(8.0, 0.0, 15.0)
    }

    init {
        defaultState = stateManager.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.NORTH)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState =
        defaultState.with(HorizontalFacingBlock.FACING, ctx.horizontalPlayerFacing)

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hit: BlockHitResult,
    ): ActionResult {
        if (world.isClient) return ActionResult.SUCCESS

        val blockEntity = world.getBlockEntity(pos) as? TotemStatueBlockEntity ?: return ActionResult.SUCCESS
        if (blockEntity.isActive()) return ActionResult.SUCCESS
        blockEntity.activate()

        //TODO

        return ActionResult.SUCCESS
    }

    override fun canPathfindThrough(state: BlockState, type: NavigationType) = false

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext,
    ): VoxelShape = SHAPE

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(HorizontalFacingBlock.FACING)
    }

    override fun getCodec(): MapCodec<out BlockWithEntity> = createCodec(::TotemStatueBlock)

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity = TotemStatueBlockEntity(pos, state)
}