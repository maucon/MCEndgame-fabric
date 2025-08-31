package de.fuballer.mcendgame.main.component.damage

import net.minecraft.component.DataComponentTypes
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.Difficulty
import net.minecraft.world.World
import kotlin.math.acos

object DamageUtil {
    fun scaleByDifficulty(
        baseDamage: Float,
        world: World,
        source: DamageSource
    ): Float {
        if (!source.isScaledWithDifficulty) return baseDamage

        return when (world.difficulty) {
            Difficulty.PEACEFUL -> 0f
            Difficulty.EASY -> (baseDamage / 2f + 1f).coerceAtMost(baseDamage)
            Difficulty.NORMAL -> baseDamage
            Difficulty.HARD -> baseDamage * 3f / 2f
        }
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

    fun applyDamageTakenAttributes(
        damageAmount: Float,
        cmd: ApplyDamageCalculationCommand,
    ): Float {
        val increasedDamage = 1 + cmd.increasedDamageTaken.sum()
        val moreDamage = cmd.moreDamageTaken.fold(1.0) { a, b -> a * (1 + b) }

        var totalFactor = increasedDamage * moreDamage
        totalFactor = totalFactor.coerceAtLeast(0.0)

        return (damageAmount * totalFactor).toFloat()
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

     fun calculateEnchantmentDamage(
        attacker: LivingEntity,
        attacked: LivingEntity,
        source: DamageSource
    ): Double {
        return EnchantmentHelper.getDamage(attacker.world as ServerWorld, attacker.weaponStack, attacked, source, 0.0F).toDouble()
    }

    fun isBlocking(
        damaged: LivingEntity,
        source: DamageSource,
    ): Boolean {
        val blocksAttacksComponent = damaged.activeItem.get(DataComponentTypes.BLOCKS_ATTACKS) ?: return false

        val vec3d = source.getPosition() ?: return false
        val vec3d2 = damaged.getRotationVector(0.0f, damaged.getHeadYaw())
        var vec3d3 = vec3d.subtract(damaged.pos)
        vec3d3 = Vec3d(vec3d3.x, 0.0, vec3d3.z).normalize()

        val angle = acos(vec3d3.dotProduct(vec3d2))
        return blocksAttacksComponent.getDamageReductionAmount(source, 1f, angle) == 1f
    }
}