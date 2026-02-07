package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

data class LookAtEntityGoalCommand(
    val mob: MobEntity,
    val target: LivingEntity,
    var canLookAt: Boolean = true,
)