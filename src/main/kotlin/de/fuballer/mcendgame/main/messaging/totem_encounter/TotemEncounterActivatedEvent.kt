package de.fuballer.mcendgame.main.messaging.totem_encounter

import net.minecraft.entity.player.PlayerEntity

data class TotemEncounterActivatedEvent(
    val player: PlayerEntity
)