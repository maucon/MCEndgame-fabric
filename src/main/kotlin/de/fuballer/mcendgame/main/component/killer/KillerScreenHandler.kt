package de.fuballer.mcendgame.main.component.killer

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.SlotActionType

class KillerScreenHandler(
    syncId: Int,
    inventory: PlayerInventory,
) : ScreenHandler(KillerService.SCREEN_HANDLER_TYPE, syncId) {
    private val killerInventory = SimpleInventory(6)

    override fun onSlotClick(
        slotIndex: Int,
        button: Int,
        actionType: SlotActionType,
        player: PlayerEntity
    ) {

    }

    override fun canUse(player: PlayerEntity) = killerInventory.canPlayerUse(player)

    // only gets called in onSlotClick which is overridden
    override fun quickMove(player: PlayerEntity, slot: Int): ItemStack = ItemStack.EMPTY
}