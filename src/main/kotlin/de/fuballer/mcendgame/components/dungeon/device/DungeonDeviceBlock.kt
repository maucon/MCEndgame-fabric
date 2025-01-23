package de.fuballer.mcendgame.components.dungeon.device

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class DungeonDeviceBlock(
    settings: Settings
) : Block(settings) {

    override fun onUse(state: BlockState?, world: World?, pos: BlockPos?, player: PlayerEntity?, hit: BlockHitResult?): ActionResult {
        println(player)
        return super.onUse(state, world, pos, player, hit) // TODO
    }
}