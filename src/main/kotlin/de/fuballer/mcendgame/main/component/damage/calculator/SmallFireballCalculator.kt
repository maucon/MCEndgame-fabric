package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.projectile.SmallFireballEntity

// TODO blaze
object SmallFireballCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is SmallFireballEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        TODO("Not yet implemented")
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        TODO("Not yet implemented")
    }
}