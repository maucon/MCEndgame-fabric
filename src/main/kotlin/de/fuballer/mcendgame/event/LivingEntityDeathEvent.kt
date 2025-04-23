package de.fuballer.mcendgame.event

import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

data class LivingEntityDeathEvent(
    val isClient: Boolean,
    val world: World,
    val entity: LivingEntity,
    val killer: LivingEntity?,
) {
    constructor(entity: LivingEntity)
            : this(entity.world.isClient, entity.world, entity, entity.attacker)
}