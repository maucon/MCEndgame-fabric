package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.component.damage.DamageUtil
import de.fuballer.mcendgame.main.component.damage.custom_type.CustomDamageTypes
import de.fuballer.mcendgame.main.component.damage.dealing.ExtendedDamageSource
import de.fuballer.mcendgame.main.util.extension.DamageTypeExtension.isOf
import net.minecraft.component.type.KineticWeaponComponent
import net.minecraft.component.type.PiercingWeaponComponent
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource

object ElementalDamageCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.type.isOf(CustomDamageTypes.ELEMENTAL)

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: ExtendedDamageSource,
        event: DamageCalculationCommand
    ) = 0f

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: ExtendedDamageSource,
        event: DamageCalculationCommand
    ): Float {
        val baseDamage = calculateBaseElementalDamage(event)
        val damageMulti = DamageUtil.calculateElementalDamageMultiplier(event)
        val damageFactor = source.damageCalculationConfig.damageFactor

        val critMulti = calculateCriticalMultiplier(event)
        return (baseDamage * critMulti * damageMulti * damageFactor).toFloat()
    }

    private fun calculateBaseElementalDamage(
        event: DamageCalculationCommand
    ): Double {
        return event.elementalDamage.sum()
    }

    private fun calculateCriticalMultiplier(event: DamageCalculationCommand): Double {
        if (!event.isCritical || !event.applyCritToElementalDamage) return 1.0
        return 1.5 + event.criticalDamageMulti.sum()
    }
}