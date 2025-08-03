package de.fuballer.mcendgame.main.component.damage

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.MathHelper

object DamageUtil {
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

    fun reduceDamageByAttributes(
        damageAmount: Float,
        cmd: ApplyDamageCalculationCommand,
    ): Float {
        val reducedDamage = 1 - cmd.reducedDamage.sum()
        val lessDamage = cmd.lessDamage.fold(1.0) { a, b -> a * (1 - b) }

        var totalReduction = reducedDamage * lessDamage
        totalReduction = totalReduction.coerceAtLeast(0.0)

        return (damageAmount * totalReduction).toFloat()
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

     fun calculateProjectileAttackDamageMultiplier(
        event: ApplyDamageCalculationCommand
    ): Double {
        var damageIncrease = 1 + event.increasedDamage.sum()
        damageIncrease *= 1 + event.increasedAttackDamage.sum()
        damageIncrease *= 1 + event.increasedProjectileDamage.sum()

        var moreDamage = event.moreDamage.fold(1.0) { a, b -> a * (b + 1) }
        moreDamage *= event.moreAttackDamage.fold(1.0) { a, b -> a * (b + 1) }
        moreDamage *= event.moreProjectileDamage.fold(1.0) { a, b -> a * (b + 1) }

        return damageIncrease * moreDamage
    }
}