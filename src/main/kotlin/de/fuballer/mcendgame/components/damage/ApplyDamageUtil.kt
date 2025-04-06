package de.fuballer.mcendgame.components.damage

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.MathHelper

object ApplyDamageUtil {
    fun reduceAttackDamageByArmor(
        armorWearer: LivingEntity,
        damageAmount: Float,
        damageSource: DamageSource,
        armor: Float,
        armorToughness: Float
    ): Float {
        val armorReductionReduction = 2.0f + armorToughness / 4.0f
        val effectiveArmor = MathHelper.clamp(armor - damageAmount / armorReductionReduction, armor * 0.2f, 20.0f)
        var damageReduction = effectiveArmor / 25.0f
        val itemStack = damageSource.weaponStack
        if (itemStack != null && armorWearer.world is ServerWorld) {
            damageReduction = MathHelper.clamp(EnchantmentHelper.getArmorEffectiveness(armorWearer.world as ServerWorld, itemStack, armorWearer, damageSource, damageReduction), 0.0f, 1.0f)
        }

        val damageMultiplier = 1.0f - damageReduction
        return damageAmount * damageMultiplier
    }

    fun reduceElementalDamageByWard(
        armorWearer: LivingEntity,
        damageAmount: Float,
        damageSource: DamageSource,
        ward: Float,
    ): Float {
        val wardReduction = 0.33F * damageAmount //TODO find reasonable values
        val effectiveWard = MathHelper.clamp(ward - wardReduction, ward / 5.0F, 10F)
        var damageReduction = effectiveWard / 12.5F

        val itemStack = damageSource.weaponStack
        if (itemStack != null && armorWearer.world is ServerWorld) {
            damageReduction = MathHelper.clamp(EnchantmentHelper.getArmorEffectiveness(armorWearer.world as ServerWorld, itemStack, armorWearer, damageSource, damageReduction), 0.0f, 1.0f)
        }

        val damageMultiplier = 1.0f - damageReduction
        return damageAmount * damageMultiplier
    }

    fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand,
    ): Float {
        val attacker = source.attacker as? LivingEntity ?: return originalDamage

        val baseDamage = calculateBaseAttackDamage(attacker, source)
        val enchantmentDamage = calculateEnchantmentDamage(attacker, attacked, source)
        val damageMulti = calculateAttackDamageMultiplier(source, event)
        val critMulti = calculateCriticalMultiplier(event)
        val otherMulti = calculateOtherMultiplier(source)

        return ((baseDamage + enchantmentDamage) * damageMulti * critMulti * otherMulti).toFloat()
    }

    fun calculateElementalDamage(
        source: DamageSource,
        event: ApplyDamageCalculationCommand,
    ): Float {
        if (source.attacker !is LivingEntity) return 0.0F

        val baseDamage = calculateBaseElementalDamage(event)
        val damageMulti = calculateElementalDamageMultiplier(source, event)
        val critMulti = calculateCriticalMultiplier(event) // TODO can elemental damage crit?
        val otherMulti = calculateOtherMultiplier(source)

        return (baseDamage * damageMulti * critMulti * otherMulti).toFloat()
    }

    private fun calculateBaseAttackDamage(
        attacker: LivingEntity,
        source: DamageSource
    ): Double {
        if (source.source is PersistentProjectileEntity) {
            return 1.5 // TODO should it be this way?
        }

        var baseDamage = attacker.getAttributeValue(EntityAttributes.ATTACK_DAMAGE)

        if (source.type.msgId == CustomDamageTypes.SWEEPING_KEY.path) {
            val sweepingRatio = attacker.getAttributeValue(EntityAttributes.SWEEPING_DAMAGE_RATIO)
            baseDamage = 1.0 + sweepingRatio * baseDamage
        }

        return baseDamage
    }

    private fun calculateBaseElementalDamage(
        event: ApplyDamageCalculationCommand
    ): Double {
        return event.elementalDamage.sum()
    }

    private fun calculateEnchantmentDamage(attacker: LivingEntity, attacked: LivingEntity, source: DamageSource): Double {
        // I mean it works :)
        // power returns values like sharpness
        return EnchantmentHelper.getDamage(attacker.world as ServerWorld, attacker.weaponStack, attacked, source, 0.0F).toDouble()
    }

    private fun calculateAttackDamageMultiplier(
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Double {
        var damageIncrease = 1 + event.increasedDamage.sum()
        damageIncrease *= 1 + event.increasedAttackDamage.sum()
        if (source.source is ProjectileEntity) {
            damageIncrease *= 1 + event.increasedProjectileDamage.sum()
        }

        var moreDamage = event.moreDamage.fold(1.0) { a, b -> a * (b + 1) }
        moreDamage *= event.moreAttackDamage.fold(1.0) { a, b -> a * (b + 1) }

        return damageIncrease * moreDamage
    }

    private fun calculateElementalDamageMultiplier(
        source: DamageSource,
        event: ApplyDamageCalculationCommand
    ): Double {
        var damageIncrease = 1 + event.increasedDamage.sum()
        damageIncrease *= 1 + event.increasedElementalDamage.sum()
        if (source.source is ProjectileEntity) {
            damageIncrease *= 1 + event.increasedProjectileDamage.sum()
        }

        var moreDamage = event.moreDamage.fold(1.0) { a, b -> a * (b + 1) }
        moreDamage *= event.moreElementalDamage.fold(1.0) { a, b -> a * (b + 1) }

        return damageIncrease * moreDamage
    }

    private fun calculateCriticalMultiplier(event: ApplyDamageCalculationCommand): Double {
        return if (event.isDamageCritical) 1.3 else 1.0 // TODO maybe rework with critical damage mods
    }

    /**
     * Refers to attack cooldown on melee and projectile velocity on projectile
     */
    private fun calculateOtherMultiplier(source: DamageSource): Double {
        val projectile = source.source as? PersistentProjectileEntity
        if (projectile != null) {
            return projectile.velocity.length()
        }

        return PlayerAccessUtil.getAttackCooldownMultiplier(source.source).toDouble()
    }
}