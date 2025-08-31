package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.fuballer.mcendgame.main.component.damage.DamageUtil
import de.fuballer.mcendgame.main.component.damage.custom_type.CustomDamageTypes
import de.fuballer.mcendgame.main.util.extension.DamageTypeExtension.isOf
import de.fuballer.mcendgame.main.util.extension.mixin.PlayerEntityMixinExtension.getAttackCooldownMultiplier
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld

object MeleeAttackCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is LivingEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        val attacker = source.attacker as? LivingEntity ?: return originalDamage

        val baseDamage = calculateBaseAttackDamage(attacker, source)
        val enchantmentDamage = DamageUtil.calculateEnchantmentDamage(attacker, attacked, source)
        val damageMulti = calculateDamageMultiplier(event)
        val critMulti = calculateCriticalMultiplier(event)
        val attackCooldownMulti = calculateAttackCooldownMultiplier(source)

        println("baseDamage = $baseDamage")
        println("enchantmentDamage = $enchantmentDamage")
        println("damageMulti = $damageMulti")
        println("critMulti = $critMulti")
        println("attackCooldownMulti = $attackCooldownMulti")

        // TODO technically crit multi is applied only to baseDamage and not to enchantmentDamage
        return ((baseDamage + enchantmentDamage) * damageMulti * critMulti * attackCooldownMulti).toFloat()
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Float {
        if (source.attacker !is LivingEntity) return 0.0F

        val baseDamage = calculateBaseElementalDamage(event)
        val damageMulti = calculateDamageMultiplier(event)
        val critMulti = calculateCriticalMultiplier(event) // TODO can elemental damage crit?
        val attackCooldownMulti = calculateAttackCooldownMultiplier(source)

        return (baseDamage * damageMulti * critMulti * attackCooldownMulti).toFloat()
    }

    private fun calculateBaseAttackDamage(
        attacker: LivingEntity,
        source: DamageSource
    ): Double {
        var baseDamage = attacker.getAttributeValue(EntityAttributes.ATTACK_DAMAGE)

        if (source.type.isOf(CustomDamageTypes.SWEEPING)) {
            val sweepingRatio = attacker.getAttributeValue(EntityAttributes.SWEEPING_DAMAGE_RATIO)
            baseDamage = 1.0 + sweepingRatio * baseDamage
        }

        return baseDamage
    }

    private fun calculateDamageMultiplier(
        event: ApplyDamageCalculationCommand
    ): Double {
        var damageIncrease = 1 + event.increasedDamage.sum()
        damageIncrease *= 1 + event.increasedAttackDamage.sum()

        var moreDamage = event.moreDamage.fold(1.0) { a, b -> a * (b + 1) }
        moreDamage *= event.moreAttackDamage.fold(1.0) { a, b -> a * (b + 1) }

        return damageIncrease * moreDamage
    }

    private fun calculateCriticalMultiplier(event: ApplyDamageCalculationCommand): Double {
        return if (event.isDamageCritical) 1.5 else 1.0 // TODO ISSUE: #74
    }

    private fun calculateAttackCooldownMultiplier(source: DamageSource): Double {
        val sourceEntity = source.source as? PlayerEntity ?: return 1.0
        return sourceEntity.getAttackCooldownMultiplier().toDouble()
    }

    private fun calculateBaseElementalDamage(
        event: ApplyDamageCalculationCommand
    ): Double {
        return event.elementalDamage.sum()
    }
}