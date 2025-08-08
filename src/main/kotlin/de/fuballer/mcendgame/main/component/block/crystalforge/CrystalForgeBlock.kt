package de.fuballer.mcendgame.main.component.block.crystalforge

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CrystalForgeBlock(
    settings: Settings
) : Block(settings) {
    companion object {
        const val ID = "crystal_forge"
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hit: BlockHitResult,
    ): ActionResult {
        if (world.isClient) return ActionResult.SUCCESS

        val screenHandlerFactory = SimpleNamedScreenHandlerFactory(
            { syncId, inventory, _ -> CrystalForgeScreenHandler(syncId, inventory) },
            Text.translatable("${CrystalForgeSettings.CONTAINER_BASE_KEY}title")
        )
        player.openHandledScreen(screenHandlerFactory)

        return ActionResult.SUCCESS
    }
}