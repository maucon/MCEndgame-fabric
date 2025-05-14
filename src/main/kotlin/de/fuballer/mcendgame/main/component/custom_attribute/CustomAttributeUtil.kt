package de.fuballer.mcendgame.main.component.custom_attribute

import net.minecraft.entity.LivingEntity
import kotlin.math.abs

object CustomAttributeUtil {
    fun LivingEntity.isLowHealth() = health <= maxHealth / 2.0

    fun LivingEntity.isHighHealth() = !isLowHealth()

    fun LivingEntity.isFullHealth() = abs(health - maxHealth) < 0.1
}