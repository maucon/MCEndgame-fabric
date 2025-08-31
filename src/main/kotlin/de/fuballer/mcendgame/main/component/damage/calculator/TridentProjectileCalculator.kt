package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.fuballer.mcendgame.main.component.damage.DamageUtil
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.server.world.ServerWorld

// TODO drowned with trident
object TridentProjectileCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is TridentEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        val attacker = source.attacker as? LivingEntity ?: return originalDamage

        val baseDamage = 8.0
        val damageMulti = DamageUtil.calculateProjectileAttackDamageMultiplier(event)
        val enchantmentDamage = DamageUtil.calculateEnchantmentDamage(attacker, attacked, source)

        return ((baseDamage + enchantmentDamage) * damageMulti).toFloat()
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ) = 0.0f
}