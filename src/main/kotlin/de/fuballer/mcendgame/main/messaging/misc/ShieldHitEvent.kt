package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack

data class ShieldHitEvent(
    val entity: LivingEntity,
    val itemStack: ItemStack,
)