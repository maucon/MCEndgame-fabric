package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.projectile.SmallFireballEntity

object SmallFireballCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is SmallFireballEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ): Float {
        // not really necessary, as entities with fire res just block small fireballs
        // but just to be safe i guess
        if (attacked.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) return 0.0F
        return 5f
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ) = 0f
}