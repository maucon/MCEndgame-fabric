package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.server.world.ServerWorld

data class ServerLivingEntityEndTickEvent(
    val entity: LivingEntity,
    val world: ServerWorld,
)