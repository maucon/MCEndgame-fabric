package de.fuballer.mcendgame.components.dungeon.device

import de.fuballer.mcendgame.components.dungeon.device.screen.DungeonDeviceScreenHandler
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos

private val TITLE = Text.translatable("container.mcendgame.dungeon_device")

class DungeonDeviceBlockEntity(
    blockPos: BlockPos,
    blockState: BlockState,
) : BlockEntity(DungeonDevice.BLOCK_ENTITY_TYPE, blockPos, blockState), NamedScreenHandlerFactory {

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity) = DungeonDeviceScreenHandler(syncId, playerInventory)

    override fun getDisplayName(): Text = TITLE
}