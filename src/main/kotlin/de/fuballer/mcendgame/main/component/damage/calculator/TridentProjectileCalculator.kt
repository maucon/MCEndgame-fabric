package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.fuballer.mcendgame.main.component.damage.DamageUtil
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.projectile.TridentEntity

// TODO drowned with trident
object TridentProjectileCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is TridentEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        val baseDamage = 8.0
        val damageMulti = DamageUtil.calculateProjectileAttackDamageMultiplier(event)
        // TODO impale damage to aquatic entities

        return (baseDamage * damageMulti).toFloat()
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ) = 0.0f
}