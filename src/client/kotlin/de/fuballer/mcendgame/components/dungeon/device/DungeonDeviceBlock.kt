package de.fuballer.mcendgame.components.dungeon.device

import de.fuballer.mcendgame.components.dungeon.device.screen.DungeonDeviceScreenHandler
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

private val TITLE = Text.translatable("container.mcendgame.dungeon_device")

class DungeonDeviceBlock(
    settings: Settings
) : Block(settings) {

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hit: BlockHitResult): ActionResult {
        if (!world.isClient) {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos))
            // TODO stats
        }

        return ActionResult.SUCCESS
    }

    override fun createScreenHandlerFactory(state: BlockState, world: World, pos: BlockPos) =
        SimpleNamedScreenHandlerFactory({ syncId, inventory, _ -> DungeonDeviceScreenHandler(syncId, inventory) }, TITLE)
}