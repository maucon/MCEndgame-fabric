package de.fuballer.mcendgame.components.dungeon.device.screen

import de.fuballer.mcendgame.components.dungeon.device.DungeonDevice
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler

class DungeonDeviceScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory
) : ScreenHandler(DungeonDevice.SCREEN_HANDLER, syncId) {

    override fun quickMove(player: PlayerEntity, slot: Int): ItemStack {
        println("WHAT")
        return ItemStack.EMPTY
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return true
    }
}