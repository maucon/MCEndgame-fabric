package de.fuballer.mcendgame.main.component.damage

import de.fuballer.mcendgame.main.component.damage.DamageUtil.reduceAttackDamageByArmor
import de.fuballer.mcendgame.main.component.damage.DamageUtil.reduceElementalDamageByWard
import de.fuballer.mcendgame.main.component.damage.calculator.*
import de.maucon.mauconframework.command.CommandGateway
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.stat.Stats
import java.util.*
import kotlin.math.min
import kotlin.math.roundToInt

typealias VanillaDamageUtil = net.minecraft.entity.DamageUtil

private val DAMAGE_CALCULATORS = listOf(
    EnderDragonCalculator,
    WitherSkullCalculator,
    WitherExplosionCalculator,
    SonicBoomCalculator,
    GuardianMagicCalculator,
    GuardianThornsCalculator,
    TridentProjectileCalculator,
    SmallFireballCalculator,
    FireballCalculator,
    PersistentProjectileCalculator,
    SnowballCalculator,
    WindChargeCalculator,
    ThornsCalculator,
    MagicDamageCalculator,
    PotionCalculator,
    MeleeAttackCalculator,
    ShulkerBulletCalculator,
    OtherProjectilesCalculator, // do not move
    BaseDamageCalculator // do not move
)

object DamageService {
    fun calculateFinalDamage(
        entity: LivingEntity,
        world: ServerWorld,
        source: DamageSource,
        shieldBlocking: Boolean,
        difficultyScaling: DifficultyScaling,
        vanillaMoreDamage: List<Double>,
        vanillaMoreDamageTaken: LinkedList<Double>,
        armadilloDamageReduction: Boolean,
        enderDragonDamageReduction: Boolean,
        originalDamage: Float
    ): Float {
        val damageCalculationCommand = DamageCalculationCommand.of(entity, world, source, shieldBlocking)
        damageCalculationCommand.moreDamage.addAll(vanillaMoreDamage)
        damageCalculationCommand.moreDamageTaken.addAll(vanillaMoreDamageTaken)

        val cmd = CommandGateway.apply(damageCalculationCommand)

        println("---------------------------------")
        println("originalDamage: $originalDamage")
        println("source: " + source.source)
        println("attacker: " + source.attacker)
        println("bug: $entity")
        println("damage type: " + source.type)
        println("bypasses armor: " + source.isIn(DamageTypeTags.BYPASSES_ARMOR))

        if (cmd.isShieldBlocking) {
            println("DAMAGE BLOCKED")
            return 0.0f
        }

        return getHitpoolDamage(originalDamage, entity, source, difficultyScaling, armadilloDamageReduction, enderDragonDamageReduction, cmd)
    }

    /** calculates the final damage dealt to the hit pool of the target */
    fun getHitpoolDamage(
        originalDamage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        difficultyScaling: DifficultyScaling,
        armadilloDamageReduction: Boolean,
        enderDragonDamageReduction: Boolean,
        cmd: DamageCalculationCommand
    ): Float {
        val damageCalculator = DAMAGE_CALCULATORS.firstOrNull { it.isActive(source) }!!
        println("damageCalculator: " + damageCalculator.javaClass.getSimpleName())

        var attackDamage = damageCalculator.calculateAttackDamage(originalDamage, attacked, source, cmd)
        println("raw attack damage: $attackDamage")
        attackDamage = calculateAttackDamageReduction(attackDamage, attacked, source, cmd)

        var elementalDamage = damageCalculator.calculateElementalDamage(originalDamage, attacked, source, cmd)
        println("raw elemental damage: $elementalDamage")
        elementalDamage = calculateElementalDamageReduction(elementalDamage, attacked, source, cmd)

        var combinedDamage = attackDamage + elementalDamage

        // Special damage calculation
        if (armadilloDamageReduction) {
            combinedDamage = (combinedDamage - 1f) / 2f
        }
         if (enderDragonDamageReduction) {
            combinedDamage = combinedDamage / 4f + min(combinedDamage, 1.0f)
        }
        combinedDamage = difficultyScaling.scaleDamage(combinedDamage)
        println("damage after difficulty scaling: $combinedDamage")

        return combinedDamage
    }

    private fun calculateAttackDamageReduction(
        damage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        cmd: DamageCalculationCommand
    ): Float {
        var attackDamage = applyArmorToDamage(damage, source, attacked)
        attackDamage = modifyAppliedDamage(source, attackDamage, attacked)
        return DamageUtil.applyDamageTakenAttributes(attackDamage, cmd)
    }

    private fun calculateElementalDamageReduction( // TODO apply protection DR to damage?
        damage: Float,
        attacked: LivingEntity,
        source: DamageSource,
        cmd: DamageCalculationCommand
    ): Float {
        var elementalDamage = applyWardToDamage(damage, source, cmd, attacked)
        elementalDamage = modifyAppliedDamage(source, elementalDamage, attacked)
        return DamageUtil.applyDamageTakenAttributes(elementalDamage, cmd)
    }

    private fun applyArmorToDamage(
        amount: Float,
        source: DamageSource,
        entity: LivingEntity
    ): Float {
        var amount = amount
        if (source.isIn(DamageTypeTags.BYPASSES_ARMOR)) return amount
        entity.damageArmor(source, amount)

        val armorToughness = entity.getAttributeValue(EntityAttributes.ARMOR_TOUGHNESS).toFloat()
        amount = reduceAttackDamageByArmor(entity, amount, source, entity.armor.toFloat(), armorToughness)

        return amount
    }

    private fun applyWardToDamage(
        amount: Float,
        source: DamageSource,
        cmd: DamageCalculationCommand,
        entity: LivingEntity
    ): Float {
        // if (source.isIn(DamageTypeTags.BYPASSES_ARMOR)) return amount; //TODO DECIDE IF APPLY
        // attacked.damageArmor(source, amount);

        var amount = amount
        val ward = cmd.ward.sum().toFloat()
        amount = reduceElementalDamageByWard(entity, amount, source, ward)

        return amount
    }

    private fun modifyAppliedDamage(
        source: DamageSource,
        amount: Float,
        entity: LivingEntity
    ): Float {
        var amount = amount
        if (source.isIn(DamageTypeTags.BYPASSES_EFFECTS)) return amount

        if (entity.hasStatusEffect(StatusEffects.RESISTANCE) && !source.isIn(DamageTypeTags.BYPASSES_RESISTANCE)) {
            val resistance = entity.getStatusEffect(StatusEffects.RESISTANCE)!!.amplifier + 1

            val resistancePercent = resistance * 0.2f
            val resistedDamage = min(amount * resistancePercent, amount)
            amount -= resistedDamage

            if (resistedDamage > 0.0f && resistedDamage < 3.4028235E37f) {
                if (entity is ServerPlayerEntity) {
                    entity.increaseStat(Stats.DAMAGE_RESISTED, (resistedDamage * 10.0f).roundToInt())
                } else if (source.attacker is ServerPlayerEntity) {
                    (source.attacker as ServerPlayerEntity).increaseStat(Stats.DAMAGE_DEALT_RESISTED, (resistedDamage * 10.0f).roundToInt())
                }
            }
        }

        if (amount <= 0.0f) return 0.0f
        if (source.isIn(DamageTypeTags.BYPASSES_ENCHANTMENTS)) return amount
        val serverWorld = entity.world as? ServerWorld ?: return amount

        val protectionAmount = EnchantmentHelper.getProtectionAmount(serverWorld, entity, source)
        amount = VanillaDamageUtil.getInflictedDamage(amount, protectionAmount)

        return amount
    }
}