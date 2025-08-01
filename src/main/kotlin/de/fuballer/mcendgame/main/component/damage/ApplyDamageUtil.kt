package de.fuballer.mcendgame.main.component.damage

import de.fuballer.mcendgame.main.component.damage.custom.CustomDamageTypes
import de.fuballer.mcendgame.main.util.extension.mixin.PersistentProjectileEntityMixinExtension.getDamage
import de.fuballer.mcendgame.main.util.extension.mixin.PlayerEntityMixinExtension.getAttackCooldownMultiplier
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.BlazeEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.*
import net.minecraft.entity.projectile.thrown.SnowballEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.MathHelper

object ApplyDamageUtil {
    fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: ApplyDamageCalculationCommand,
    ): Float {
        val attacker = source.attacker as? LivingEntity ?: return originalDamage

        val baseDamage = calculateBaseAttackDamage(attacker, attacked, source, originalDamage.toDouble())
        println("baseDamage = $baseDamage")
        val enchantmentDamage = calculateEnchantmentDamage(attacker, attacked, source)
        println("enchantmentDamage = $enchantmentDamage")
        val damageMulti = calculateAttackDamageMultiplier(event)
        println("damageMulti = $damageMulti")
        val critMulti = calculateCriticalMultiplier(event)
        println("critMulti = $critMulti")
        val otherMulti = calculateOtherMultiplier(source)
        println("otherMulti = $otherMulti")

        val full = ((baseDamage + enchantmentDamage) * damageMulti * critMulti * otherMulti).toFloat()
        println("full = $full")
        return full
    }

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
        val reducedDamage = 1 + cmd.reducedDamage.sum()
        val lessDamageFactor = cmd.lessDamage.fold(1.0) { a, b -> a * (1 - b) }
        return (damageAmount / reducedDamage * lessDamageFactor).toFloat()
    }

    fun calculateElementalDamage(
        source: DamageSource,
        event: ApplyDamageCalculationCommand,
    ): Float {
        if (source.attacker !is LivingEntity) return 0.0F

        val baseDamage = calculateBaseElementalDamage(event)
        val damageMulti = calculateElementalDamageMultiplier(event)
        val critMulti = calculateCriticalMultiplier(event) // TODO can elemental damage crit?
        val otherMulti = calculateOtherMultiplier(source)

        return (baseDamage * damageMulti * critMulti * otherMulti).toFloat()
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

    fun calculateBaseAttackDamage(
        attacker: LivingEntity,
        attacked: LivingEntity,
        source: DamageSource,
        originalDamage: Double
    ): Double {
        val persistentProjectile = source.source as? PersistentProjectileEntity
        if (persistentProjectile != null) {
            // tridents to 8 damage
            if (persistentProjectile is TridentEntity) return 8.0

            return persistentProjectile.getDamage()
        }

        // TODO snowman
        // TODO trident dudes
        // TODO fireworks, fireball
        // TODO wither skull

        val projectile = source.source as? ProjectileEntity
        if (projectile != null) {
            // snowballs do damage to blazes
            if (projectile is SnowballEntity && attacked is BlazeEntity) return 3.0
            if (projectile is FireworkRocketEntity) return originalDamage
            if (projectile is FireballEntity) return originalDamage
            if (projectile is SmallFireballEntity) return originalDamage

            return 0.0
        }

        var baseDamage = attacker.getAttributeValue(EntityAttributes.ATTACK_DAMAGE)

        if (source.type.msgId == CustomDamageTypes.SWEEPING_KEY.path) {
            val sweepingRatio = attacker.getAttributeValue(EntityAttributes.SWEEPING_DAMAGE_RATIO)
            baseDamage = 1.0 + sweepingRatio * baseDamage
        }

        return baseDamage
    }

    fun calculateBaseElementalDamage(
        event: ApplyDamageCalculationCommand
    ): Double {
        return event.elementalDamage.sum()
    }

    fun calculateEnchantmentDamage(attacker: LivingEntity, attacked: LivingEntity, source: DamageSource): Double {
        val projectile = source.source as? ProjectileEntity
        val weaponStack = if (projectile != null) {
            projectile.weaponStack ?: return 0.0
        } else attacker.weaponStack

        return EnchantmentHelper.getDamage(attacker.world as ServerWorld, weaponStack, attacked, source, 0.0F).toDouble()
    }

    fun calculateAttackDamageMultiplier(
        event: ApplyDamageCalculationCommand
    ): Double {
        var damageIncrease = 1 + event.increasedDamage.sum()
        damageIncrease *= 1 + event.increasedAttackDamage.sum()
        if (event.isProjectile) {
            damageIncrease *= 1 + event.increasedProjectileDamage.sum()
        }

        var moreDamage = event.moreDamage.fold(1.0) { a, b -> a * (b + 1) }
        moreDamage *= event.moreAttackDamage.fold(1.0) { a, b -> a * (b + 1) }
        if (event.isProjectile) {
            moreDamage *= event.moreProjectileDamage.fold(1.0) { a, b -> a * (b + 1) }
        }

        return damageIncrease * moreDamage
    }

    fun calculateElementalDamageMultiplier(
        event: ApplyDamageCalculationCommand
    ): Double {
        var damageIncrease = 1 + event.increasedDamage.sum()
        damageIncrease *= 1 + event.increasedElementalDamage.sum()
        if (event.isProjectile) {
            damageIncrease *= 1 + event.increasedProjectileDamage.sum()
        }

        var moreDamage = event.moreDamage.fold(1.0) { a, b -> a * (b + 1) }
        moreDamage *= event.moreElementalDamage.fold(1.0) { a, b -> a * (b + 1) }
        if (event.isProjectile) {
            moreDamage *= event.moreProjectileDamage.fold(1.0) { a, b -> a * (b + 1) }
        }

        return damageIncrease * moreDamage
    }

    fun calculateCriticalMultiplier(event: ApplyDamageCalculationCommand): Double {
        return if (event.isDamageCritical) 1.3 else 1.0 // TODO ISSUE: #74
    }

    /**
     * Refers to attack cooldown on melee and projectile velocity on projectile
     */
    fun calculateOtherMultiplier(source: DamageSource): Double {
        val sourceEntity = source.source
        if (sourceEntity is PersistentProjectileEntity) {
            // tridents only deal their base damage
            if (sourceEntity is TridentEntity) return 1.0

            return sourceEntity.velocity.length()
        }

        if (sourceEntity !is PlayerEntity) return 1.0
        return sourceEntity.getAttackCooldownMultiplier().toDouble()
    }
}