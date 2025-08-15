package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.projectile.AbstractWindChargeEntity
import net.minecraft.entity.projectile.BreezeWindChargeEntity
import net.minecraft.entity.projectile.SmallFireballEntity
import net.minecraft.entity.projectile.WindChargeEntity

object WindChargeCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is AbstractWindChargeEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        return 1f
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ) = 0f
}