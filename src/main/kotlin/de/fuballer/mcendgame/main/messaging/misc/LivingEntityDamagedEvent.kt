package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource

data class LivingEntityDamagedEvent(
    val damaged: LivingEntity,
    val damageSource: DamageSource,
    val amount: Float,
)