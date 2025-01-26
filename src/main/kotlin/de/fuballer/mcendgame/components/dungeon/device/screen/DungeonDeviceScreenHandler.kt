package de.fuballer.mcendgame.components.dungeon.device.screen

import de.fuballer.mcendgame.components.dungeon.device.DungeonDevice
import de.fuballer.mcendgame.components.dungeon.device.DungeonDeviceSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class DungeonDeviceScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    private val inventory: Inventory
) : ScreenHandler(DungeonDevice.SCREEN_HANDLER, syncId) {

    constructor(syncId: Int, playerInventory: PlayerInventory)
            : this(syncId, playerInventory, SimpleInventory(DungeonDeviceSettings.INVENTORY_SIZE))

    init {
        checkSize(inventory, DungeonDeviceSettings.INVENTORY_SIZE)
        inventory.onOpen(playerInventory.player)

        // Our inventory
        for (row in 0..1) {
            for (col in 0..1) {
                this.addSlot(Slot(inventory, col + row * 2, 70 + col * 18, 26 + row * 18))
            }
        }

        // The player inventory
        for (row in 0..2) {
            for (col in 0..8) {
                this.addSlot(Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18))
            }
        }

        // The player hotbar
        for (row in 0..8) {
            this.addSlot(Slot(playerInventory, row, 8 + row * 18, 142))
        }
    }

    override fun quickMove(player: PlayerEntity, slotIndex: Int): ItemStack {
        val slot = slots[slotIndex]
        if (!slot.hasStack()) return ItemStack.EMPTY

        val originalStack = slot.stack
        val newStack = originalStack.copy()
        if (slotIndex < inventory.size()) {
            val itemInserted = !this.insertItem(originalStack, inventory.size(), slots.size, true)
            if (itemInserted) return ItemStack.EMPTY
        } else if (!this.insertItem(originalStack, 0, inventory.size(), false)) {
            return ItemStack.EMPTY
        }

        if (originalStack.isEmpty) {
            slot.stack = ItemStack.EMPTY
        } else {
            slot.markDirty()
        }

        return newStack!!
    }

    override fun canUse(player: PlayerEntity) = inventory.canPlayerUse(player)
}