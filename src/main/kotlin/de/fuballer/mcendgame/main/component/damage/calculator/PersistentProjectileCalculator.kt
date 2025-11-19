package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.component.damage.DamageUtil
import de.fuballer.mcendgame.main.component.damage.dealing.ExtendedDamageSource
import de.fuballer.mcendgame.main.util.extension.mixin.PersistentProjectileEntityMixinExtension.getDamage
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.server.world.ServerWorld
import kotlin.math.ceil
import kotlin.random.Random

// skeleton arrows (bogged, stray)
object PersistentProjectileCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.source is PersistentProjectileEntity

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: ExtendedDamageSource,
        event: DamageCalculationCommand
    ): Float {
        val attacker = source.attacker as? LivingEntity ?: return originalDamage

        val baseDamage = calculateBaseAttackDamage(source)
        val enchantmentDamage = calculateEnchantmentDamage(attacker, attacked, source)
        val projectileSpeedMulti = calculateOtherMultiplier(source)
        val critMulti = calculateCriticalMultiplier(event)
        val damageMulti = DamageUtil.calculateAttackDamageMultiplier(event)

        val vanillaLikeDamage = ceil((baseDamage + enchantmentDamage) * projectileSpeedMulti)
        return (vanillaLikeDamage * critMulti * damageMulti).toFloat()
    }

    override fun calculateElementalDamage( // TODO
        originalDamage: Float,
        attacked: LivingEntity,
        source: ExtendedDamageSource,
        event: DamageCalculationCommand
    ): Float {
        if (source.attacker !is LivingEntity) return 0.0F

        val baseDamage = calculateBaseElementalDamage(event)
        val damageMulti = DamageUtil.calculateAttackDamageMultiplier(event)
        val critMulti = calculateCriticalMultiplier(event) // TODO can elemental damage crit?
        val projectileSpeedMulti = calculateOtherMultiplier(source)

        return (baseDamage * damageMulti * critMulti * projectileSpeedMulti).toFloat()
    }

    private fun calculateBaseAttackDamage(source: DamageSource): Double {
        val persistentProjectile = source.source as PersistentProjectileEntity
        return persistentProjectile.getDamage()
    }

    private fun calculateEnchantmentDamage(attacker: LivingEntity, attacked: LivingEntity, source: DamageSource): Double {
        val projectile = source.source as ProjectileEntity
        val weaponStack = projectile.weaponStack ?: return 0.0

        return EnchantmentHelper.getDamage(attacker.world as ServerWorld, weaponStack, attacked, source, 0.0F).toDouble()
    }

    private fun calculateCriticalMultiplier(event: DamageCalculationCommand): Double {
        if (!event.isDamageCritical) return 1.0

        // simulating minecraft projectile (arrow) crit
        return 1 + Random.nextDouble() * 0.5 // TODO ISSUE: #74
    }

    private fun calculateOtherMultiplier(source: DamageSource): Double {
        val sourceEntity = source.source as PersistentProjectileEntity
        return sourceEntity.velocity.length()
    }

    private fun calculateBaseElementalDamage(
        event: DamageCalculationCommand
    ): Double {
        return event.elementalDamage.sum()
    }
}