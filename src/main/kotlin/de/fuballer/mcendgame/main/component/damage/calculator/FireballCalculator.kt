package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.GhastEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.FireballEntity

object FireballCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is FireballEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ): Float {
        // redirected fireballs deal 1000 damage to ghasts
        if (source.attacker is PlayerEntity && attacked is GhastEntity) return 1000f
        return 6f
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ) = 0f
}