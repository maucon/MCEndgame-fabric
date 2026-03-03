package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

data class PlayerEntityDeathEvent(
    val isClient: Boolean,
    val world: World,
    val player: PlayerEntity,
    val killer: LivingEntity?,
) {
    constructor(player: PlayerEntity)
            : this(player.entityWorld.isClient, player.entityWorld, player, player.attacker)
}