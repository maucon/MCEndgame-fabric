package de.fuballer.mcendgame.main.component.entity.custom.entities.scarred_one

import net.minecraft.server.network.ServerPlayerEntity

data class ScarredOneDespawnEvent(
    /**
     * the player who accepted/denied
     */
    val player: ServerPlayerEntity,
    val entity: ScarredOneEntity,
    val accepted: Boolean,
)