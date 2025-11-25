package de.fuballer.mcendgame.main.messaging.dungeon

import de.fuballer.mcendgame.main.component.block.dungeon_device.DungeonDeviceBlockEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.collection.DefaultedList

/**
 * only server-side
 */
data class OpenDungeonButtonPressedEvent(
    val blockEntity: BlockEntity,
    val player: PlayerEntity,
    val dungeonDeviceBlockEntity: DungeonDeviceBlockEntity,
)