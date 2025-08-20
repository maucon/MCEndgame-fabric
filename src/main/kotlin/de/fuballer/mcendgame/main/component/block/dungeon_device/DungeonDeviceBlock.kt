package de.fuballer.mcendgame.main.component.block.dungeon_device

import com.mojang.serialization.MapCodec
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.ItemScatterer
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class DungeonDeviceBlock(
    settings: Settings
) : BlockWithEntity(settings) {
    companion object {
        const val ID = "dungeon_device"
    }

    override fun getCodec(): MapCodec<out BlockWithEntity> = createCodec(::DungeonDeviceBlock)

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hit: BlockHitResult): ActionResult {
        if (!world.isClient) {
            val screenHandlerFactory = state.createScreenHandlerFactory(world, pos)
            player.openHandledScreen(screenHandlerFactory)

            // TODO stats
        }

        return ActionResult.SUCCESS
    }

    override fun onStateReplaced(
        state: BlockState,
        world: ServerWorld,
        pos: BlockPos,
        moved: Boolean
    ) {
        if (!state.hasBlockEntity()) return

        val blockEntity = world.getBlockEntity(pos) ?: return
        val dungeonDeviceBlockEntity = blockEntity as? DungeonDeviceBlockEntity ?: return

        ItemScatterer.spawn(world, pos, dungeonDeviceBlockEntity)
        world.updateComparators(pos, this)
        super.onStateReplaced(state, world, pos, moved)
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity = DungeonDeviceBlockEntity(pos, state)
}