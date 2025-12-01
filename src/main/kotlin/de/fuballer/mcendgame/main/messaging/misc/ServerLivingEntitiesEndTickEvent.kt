package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.server.world.ServerWorld

data class ServerLivingEntitiesEndTickEvent(
    val entities: List<LivingEntity>,
    val world: ServerWorld,
)