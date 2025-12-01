package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.server.world.ServerWorld

data class DamageItemStackCommand(
    var baseDamage: Int,
    val world: ServerWorld,
)