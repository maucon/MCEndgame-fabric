package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.BlazeEntity
import net.minecraft.entity.projectile.thrown.SnowballEntity

object SnowballCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is SnowballEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        if (attacked is BlazeEntity) return 3.0f
        return 0.0f
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ) = 0.0f
}