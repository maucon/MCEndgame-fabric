package de.fuballer.mcendgame.components.damage

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
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
        source: DamageSource,
        event: ApplyDamageCalculationEvent,
    ): Float {
        val attacker = source.attacker as? LivingEntity ?: return originalDamage

        // TODO if proj
        // TODO sweep

        val baseDamage = attacker.getAttributeValue(EntityAttributes.ATTACK_DAMAGE)

        var damageIncrease = 1 + event.increasedDamage.sum()
        damageIncrease *= 1 + event.increasedAttackDamage.sum()
        if (source.source is ProjectileEntity) {
            damageIncrease *= 1 + event.increasedProjectileDamage.sum()
        }

        var moreDamage = event.moreDamage.fold(1.0) { a, b -> a * (b + 1) }
        moreDamage *= event.moreAttackDamage.fold(1.0) { a, b -> a * (b + 1) }

        val criticalMultiplier = if (event.isDamageCritical) 1.5 else 1.0

        //TODO enchants

        val attackCooldownMultiplier = DamageUtil.getAttackCooldownMultiplier(source.source)

        return (baseDamage * damageIncrease * moreDamage * criticalMultiplier * attackCooldownMultiplier).toFloat()
    }

    fun calculateElementalDamage(
        source: DamageSource,
        event: ApplyDamageCalculationEvent,
    ): Float {
        // TODO if proj
        // TODO sweep

        val baseDamage = event.elementalDamage.sum()

        var damageIncrease = 1 + event.increasedDamage.sum()
        damageIncrease *= 1 + event.increasedElementalDamage.sum()
        if (source.source is ProjectileEntity) {
            damageIncrease *= 1 + event.increasedProjectileDamage.sum()
        }

        var moreDamage = event.moreDamage.fold(1.0) { a, b -> a * (b + 1) }
        moreDamage *= event.moreElementalDamage.fold(1.0) { a, b -> a * (b + 1) }

        val criticalMultiplier = if (event.isDamageCritical) 1.5 else 1.0

        //TODO enchants

        val attackCooldownMultiplier = DamageUtil.getAttackCooldownMultiplier(source.source)

        return (baseDamage * damageIncrease * moreDamage * criticalMultiplier * attackCooldownMultiplier).toFloat()
    }


}