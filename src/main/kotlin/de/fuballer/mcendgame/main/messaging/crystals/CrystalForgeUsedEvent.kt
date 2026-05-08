package de.fuballer.mcendgame.main.messaging.crystals

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item

data class CrystalForgeUsedEvent(
    val player: PlayerEntity,
    val crystal: Item,
)