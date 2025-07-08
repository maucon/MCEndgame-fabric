package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack

data class EquipmentChangeEvent(
    var entity: LivingEntity,
    var slot: EquipmentSlot,
    var oldStack: ItemStack,
    var newStack: ItemStack,
)