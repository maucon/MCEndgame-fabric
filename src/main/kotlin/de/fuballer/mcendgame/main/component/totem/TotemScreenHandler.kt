package de.fuballer.mcendgame.main.component.totem

import de.fuballer.mcendgame.main.component.screen.CustomScreenHandlerTypes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot
import net.minecraft.screen.slot.SlotActionType

class TotemScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
) : ScreenHandler(CustomScreenHandlerTypes.TOTEM, syncId) {
    private val totemInventory = SimpleInventory(5)

    init {
        for (i in 0 until 5) {
            addSlot(Slot(totemInventory, i, 44 + i * 18, 20))
        }

        addPlayerSlots(playerInventory, 8, 51)
    }

    override fun onSlotClick(
        slotIndex: Int,
        button: Int,
        actionType: SlotActionType,
        player: PlayerEntity
    ) {
    }

    // only gets called in onSlotClick which is overridden
    override fun quickMove(player: PlayerEntity, slot: Int): ItemStack = ItemStack.EMPTY

    override fun canUse(player: PlayerEntity) = totemInventory.canPlayerUse(player)
}