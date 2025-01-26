package de.fuballer.mcendgame.components.dungeon.device.screen

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.Generic3x3ContainerScreenHandler

@Environment(EnvType.CLIENT)
class DungeonDeviceScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory
) : Generic3x3ContainerScreenHandler(syncId, playerInventory) {



    override fun quickMove(player: PlayerEntity, slot: Int): ItemStack {
        return super.quickMove(player, slot)
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return true
    }
}