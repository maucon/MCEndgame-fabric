package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.server.world.ServerWorld

data class ServerLivingEntitiesEveryFiveTicksEvent(
    val entities: List<LivingEntity>,
    val world: ServerWorld,
)