package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

data class LivingEntityDeathEvent(
    val isClient: Boolean,
    val world: World,
    val entity: LivingEntity,
    val killer: LivingEntity?,
) {
    constructor(entity: LivingEntity)
            : this(entity.entityWorld.isClient, entity.entityWorld, entity, entity.attacker)
}