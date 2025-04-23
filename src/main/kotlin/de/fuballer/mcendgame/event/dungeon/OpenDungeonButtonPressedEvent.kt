package de.fuballer.mcendgame.event.dungeon

import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity

/**
 * only server-side
 */
data class OpenDungeonButtonPressedEvent(
    val blockEntity: BlockEntity,
    val player: PlayerEntity,
)