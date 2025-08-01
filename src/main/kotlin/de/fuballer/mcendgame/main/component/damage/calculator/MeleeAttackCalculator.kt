package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.fuballer.mcendgame.main.component.damage.ApplyDamageUtil
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource

object MeleeAttackCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is LivingEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        val attacker = source.attacker as? LivingEntity ?: return originalDamage

        val baseDamage = ApplyDamageUtil.calculateBaseAttackDamage(attacker, attacked, source, originalDamage.toDouble())
        val enchantmentDamage = ApplyDamageUtil.calculateEnchantmentDamage(attacker, attacked, source)
        val damageMulti = ApplyDamageUtil.calculateAttackDamageMultiplier(event)
        val critMulti = ApplyDamageUtil.calculateCriticalMultiplier(event)
        val otherMulti = ApplyDamageUtil.calculateOtherMultiplier(source)

        return ((baseDamage + enchantmentDamage) * damageMulti * critMulti * otherMulti).toFloat()
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        if (source.attacker !is LivingEntity) return 0.0F

        val baseDamage = ApplyDamageUtil.calculateBaseElementalDamage(event)
        val damageMulti = ApplyDamageUtil.calculateElementalDamageMultiplier(event)
        val critMulti = ApplyDamageUtil.calculateCriticalMultiplier(event) // TODO can elemental damage crit?
        val otherMulti = ApplyDamageUtil.calculateOtherMultiplier(source)

        return (baseDamage * damageMulti * critMulti * otherMulti).toFloat()
    }
}