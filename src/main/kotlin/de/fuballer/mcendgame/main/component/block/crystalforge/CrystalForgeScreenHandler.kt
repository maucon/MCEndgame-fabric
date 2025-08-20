package de.fuballer.mcendgame.main.component.block.crystalforge

import de.fuballer.mcendgame.main.component.block.crystalforge.slot.CrystalSlot
import de.fuballer.mcendgame.main.component.block.crystalforge.slot.ForgeableEquipmentSlot
import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import de.fuballer.mcendgame.main.component.screen.CustomScreenHandlerTypes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class CrystalForgeScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
) : ScreenHandler(CustomScreenHandlerTypes.CRYSTAL_FORGE, syncId) {
    private val inputInventory = SimpleInventory(2)

    init {
        addSlot(ForgeableEquipmentSlot(inputInventory, 0, 44, 20))
        addSlot(CrystalSlot(inputInventory, 1, 116, 20))

        // player inventory
        for (row in 0..2) {
            for (col in 0..8) {
                addSlot(Slot(playerInventory, 9 + row * 9 + col, 8 + col * 18, 84 + row * 18))
            }
        }

        // player hotbar
        for (hotbarSlot in 0..8) {
            addSlot(Slot(playerInventory, hotbarSlot, 8 + hotbarSlot * 18, 142))
        }
    }

    override fun quickMove(player: PlayerEntity, slotIndex: Int): ItemStack {
        val slot = slots[slotIndex]
        if (!slot.hasStack()) return ItemStack.EMPTY

        if (slotIndex >= inputInventory.size()) return quickMoveToInputInventory(slot.stack)
        return quickMoveToPlayerInventory(slot.stack)
    }

    private fun quickMoveToInputInventory(
        itemStack: ItemStack,
    ) = if (insertItem(itemStack, 0, inputInventory.size(), false)) itemStack else ItemStack.EMPTY

    private fun quickMoveToPlayerInventory(
        itemStack: ItemStack,
    ) = if (insertItem(itemStack, inputInventory.size(), slots.size, true)) itemStack else ItemStack.EMPTY

    override fun canUse(player: PlayerEntity) = true

    override fun onClosed(player: PlayerEntity) {
        super.onClosed(player)
        dropInventory(player, inputInventory)
    }

    fun forge() {
        val toForgeStack = inputInventory.getStack(0)
        val crystalStack = inputInventory.getStack(1)
        val crystalItem = crystalStack.item as? CrystalItem ?: return

        if (crystalItem.canForge(toForgeStack) != null) return

        val forgedStack = crystalItem.forge(toForgeStack)
        crystalStack.decrement(1)
        inputInventory.setStack(0, forgedStack)

        sendContentUpdates()
    }
}