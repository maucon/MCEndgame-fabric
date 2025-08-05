package de.fuballer.mcendgame.main.component.killer

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot
import net.minecraft.screen.slot.SlotActionType

class KillerScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
) : ScreenHandler(KillerService.SCREEN_HANDLER_TYPE, syncId) {
    private val killerInventory = SimpleInventory(6)

    init {
        for (armorSlot in 0..3) {
            addSlot(Slot(killerInventory, armorSlot, 8, 8 + armorSlot * 18))
        }
        for (weaponSlot in 0..1) {
            addSlot(Slot(killerInventory, weaponSlot + 4, 8, 84 + weaponSlot * 18))
        }
    }

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