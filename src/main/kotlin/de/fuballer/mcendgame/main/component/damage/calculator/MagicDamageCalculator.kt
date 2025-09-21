package de.fuballer.mcendgame.main.component.damage.calculator

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.util.extension.DamageTypeExtension.isOf
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageTypes

// poison potion effect
object MagicDamageCalculator : DamageCalculator {
    override fun isActive(source: DamageSource) = source.type.isOf(DamageTypes.MAGIC)

    override fun calculateAttackDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ) = 0f

    override fun calculateElementalDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        event: DamageCalculationCommand
    ) = originalDamage // TODO think about if ward protects against magic damage
}