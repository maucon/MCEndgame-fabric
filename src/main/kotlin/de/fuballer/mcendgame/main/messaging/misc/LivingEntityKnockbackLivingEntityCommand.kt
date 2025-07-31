package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity

data class LivingEntityKnockbackLivingEntityCommand(
    val target: LivingEntity,
    val attacker: LivingEntity,
    var strength: Double,
)