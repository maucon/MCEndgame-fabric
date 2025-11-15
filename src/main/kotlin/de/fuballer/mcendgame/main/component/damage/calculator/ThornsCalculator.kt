package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.component.damage.DamageUtil
import de.fuballer.mcendgame.main.util.extension.DamageTypeExtension.isOf
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageTypes
import kotlin.random.Random

object ThornsCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.type.isOf(DamageTypes.THORNS)

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ): Float {
        val baseDamage = Random.nextFloat() * 4 + 1 // 1 to 5 damage
        val damageMulti = DamageUtil.calculateGenericDamageMultiplier(event)
        return (baseDamage * damageMulti).toFloat()
    }

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ) = 0f
}