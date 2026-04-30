package de.fuballer.mcendgame.main.messaging.dungeon

import de.fuballer.mcendgame.main.component.block.blocks.dungeon_device.DungeonDeviceBlockEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity

/**
 * only server-side
 */
data class OpenDungeonButtonPressedEvent(
    val blockEntity: BlockEntity,
    val player: PlayerEntity,
    val dungeonDeviceBlockEntity: DungeonDeviceBlockEntity,
)