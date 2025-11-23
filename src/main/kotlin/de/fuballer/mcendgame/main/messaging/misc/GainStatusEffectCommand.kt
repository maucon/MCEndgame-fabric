package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance

data class GainStatusEffectCommand(
    val entity: LivingEntity,
    var effect: StatusEffectInstance,
)