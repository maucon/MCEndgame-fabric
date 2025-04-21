package de.fuballer.mcendgame.event

import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity

data class OpenDungeonButtonPressedEvent(
    val blockEntity: BlockEntity,
    val player: PlayerEntity,
)