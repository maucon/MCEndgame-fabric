package de.fuballer.mcendgame.event

import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity

data class DungeonOpenEvent(
    val blockEntity: BlockEntity,
    val player: PlayerEntity,
) {
    companion object {
        val NOTIFIER = Notifier<DungeonOpenEvent>()
    }
}