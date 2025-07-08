package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity

data class LivingEntityDodgedEvent(
    val entity: LivingEntity,
    val attacker: Entity?,
)