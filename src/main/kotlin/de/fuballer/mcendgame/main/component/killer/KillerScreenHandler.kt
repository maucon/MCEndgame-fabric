package de.fuballer.mcendgame.main.component.killer

import de.fuballer.mcendgame.main.component.entity.custom.CustomEntities
import de.fuballer.mcendgame.main.component.entity.custom.entities.arachne.ArachneEntity
import de.fuballer.mcendgame.main.component.inventory.EmptySpriteSlot
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.screen.PlayerScreenHandler
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.util.Identifier

private val SLOT_SPRITES = listOf(
    PlayerScreenHandler.EMPTY_HELMET_SLOT_TEXTURE,
    PlayerScreenHandler.EMPTY_CHESTPLATE_SLOT_TEXTURE,
    PlayerScreenHandler.EMPTY_LEGGINGS_SLOT_TEXTURE,
    PlayerScreenHandler.EMPTY_BOOTS_SLOT_TEXTURE,
    Identifier.ofVanilla("container/slot/sword"),
    PlayerScreenHandler.EMPTY_OFF_HAND_SLOT_TEXTURE,
)

private val EQUIPMENT_SLOTS = mapOf(
    EquipmentSlot.HEAD to 0,
    EquipmentSlot.CHEST to 1,
    EquipmentSlot.LEGS to 2,
    EquipmentSlot.FEET to 3,
    EquipmentSlot.MAINHAND to 4,
    EquipmentSlot.OFFHAND to 5,
)

class KillerScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
) : ScreenHandler(KillerService.SCREEN_HANDLER_TYPE, syncId) {
    var killer: LivingEntity? = null
    private val killerInventory = SimpleInventory(6)

    init {
        for (armorSlot in 0..3) {
            addSlot(EmptySpriteSlot(killerInventory, armorSlot, 8, 8 + armorSlot * 18, SLOT_SPRITES[armorSlot]))
        }
        for (weaponSlot in 0..1) {
            addSlot(EmptySpriteSlot(killerInventory, weaponSlot + 4, 8, 84 + weaponSlot * 18, SLOT_SPRITES[4 + weaponSlot]))
        }

        loadKiller(playerInventory.player)
    }

    private fun loadKiller(
        player: PlayerEntity
    ) {
        //TODO get actual killer
        killer = ArachneEntity(CustomEntities.ARACHNE, player.world) ?: return
        killer!!.equipStack(EquipmentSlot.CHEST, ItemStack(Items.DIAMOND_CHESTPLATE))
        killer!!.equipStack(EquipmentSlot.MAINHAND, ItemStack(Items.GOLDEN_AXE))

        EQUIPMENT_SLOTS.forEach {
            val stack = killer!!.getEquippedStack(it.key)
            killerInventory.setStack(it.value, stack)
        }

        listOf(
            StatusEffectInstance(StatusEffects.STRENGTH),
            StatusEffectInstance(StatusEffects.SPEED, 200, 3),
            StatusEffectInstance(StatusEffects.RESISTANCE, StatusEffectInstance.INFINITE, 100),
        ).forEach { killer!!.addStatusEffect(it) }
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