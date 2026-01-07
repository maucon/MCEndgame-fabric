package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

data class PlayerTakeShieldHitCommand(
    val player: PlayerEntity,
    val itemStack: ItemStack,
    var shieldCooldown: Float,
)