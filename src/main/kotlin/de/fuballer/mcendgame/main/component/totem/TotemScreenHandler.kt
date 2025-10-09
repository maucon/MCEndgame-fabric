package de.fuballer.mcendgame.main.component.totem

import de.fuballer.mcendgame.main.component.item.custom.totem.TotemType
import de.fuballer.mcendgame.main.component.screen.CustomScreenHandlerTypes
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isInDungeonWorld
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.SlotActionType

class TotemScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    totems: List<ItemStack> = listOf(),
    private val totemService: TotemService? = null,
) : ScreenHandler(CustomScreenHandlerTypes.TOTEM, syncId) {
    private val totemInventory = SimpleInventory(8)

    init {
        addTotemSlots()
        fillTotemSlots(totems)

        addPlayerSlots(playerInventory, 8, 87)
    }

    private fun addTotemSlots() {
        for (i in 0..4) addSlot(TotemSlot(totemInventory, i, 26 + 27 * i, 56, TotemType.BASIC))
        addSlot(TotemSlot(totemInventory, 5, 48, 29, TotemType.EFFECT))
        addSlot(TotemSlot(totemInventory, 6, 112, 29, TotemType.EFFECT))
        addSlot(TotemSlot(totemInventory, 7, 80, 20, TotemType.ABILITY))
    }

    private fun fillTotemSlots(totems: List<ItemStack>) {
        for ((index, totem) in totems.withIndex()) {
            if (index >= totemInventory.size()) break
            setStackInSlot(index, 0, totem)
        }
    }

    override fun onClosed(player: PlayerEntity) {
        super.onClosed(player)
        totemService?.savePlayerTotems(player, totemInventory)
    }

    override fun onSlotClick(slotIndex: Int, button: Int, actionType: SlotActionType, player: PlayerEntity) {
        if (player.isInDungeonWorld()) return //TODO allow actions that don't alter equipped totems
        super.onSlotClick(slotIndex, button, actionType, player)
    }

    override fun quickMove(player: PlayerEntity, slotIndex: Int): ItemStack {
        val slot = slots[slotIndex]
        if (!slot.hasStack()) return ItemStack.EMPTY
        val stack = slot.stack

        if (slotIndex < 8) {
            if (!insertItem(stack, 8, 44, true)) return ItemStack.EMPTY
            return stack
        } else {
            if (!insertItem(stack, 0, 8, false)) return ItemStack.EMPTY
            return stack
        }
    }

    override fun canUse(player: PlayerEntity) = totemInventory.canPlayerUse(player)
}