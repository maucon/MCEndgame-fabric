package de.fuballer.mcendgame.main.component.block.crystalforge

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
        addSlot(Slot(inputInventory, 0, 44, 20))
        addSlot(Slot(inputInventory, 1, 116, 20))

        // player hotbar
        for (hotbarSlot in 0..8) {
            addSlot(Slot(playerInventory, hotbarSlot, 8 + hotbarSlot * 18, 142))
        }

        // player inventory
        for (row in 0..2) {
            for (col in 0..8) {
                addSlot(Slot(playerInventory, 9 + row * 9 + col, 8 + col * 18, 84 + row * 18))
            }
        }
    }

    override fun quickMove(player: PlayerEntity, slot: Int): ItemStack {
        return ItemStack.EMPTY //TODO implement quickMove
    }

    override fun canUse(player: PlayerEntity) = true

    override fun onClosed(player: PlayerEntity) {
        super.onClosed(player)
        dropInventory(player, inputInventory)
    }

    fun forge() {
        val toForgeStack = inputInventory.getStack(0)
        val crystalStack = inputInventory.getStack(1)
        val crystalItem = crystalStack.item as? CrystalItem ?: return

        if (!crystalItem.canForge(toForgeStack)) return
        val forgedStack = crystalItem.forge(toForgeStack)
        inputInventory.setStack(0, forgedStack)

        sendContentUpdates()
    }
}