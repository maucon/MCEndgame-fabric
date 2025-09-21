package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.component.damage.DamageUtil
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource

/**
 * Used when no other specific calculator is used
 * - Fall Damage
 * */
object BaseDamageCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = true

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ): Float {
        val damageMulti = DamageUtil.calculateAttackDamageMultiplier(event)
        return (originalDamage * damageMulti).toFloat()
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ) = 0f // per default only attack damage is dealt
}