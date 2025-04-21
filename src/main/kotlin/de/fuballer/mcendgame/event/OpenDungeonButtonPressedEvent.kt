package de.fuballer.mcendgame.event

import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import java.util.*

data class OpenDungeonButtonPressedEvent(
    val blockEntity: BlockEntity,
    val player: PlayerEntity,
)