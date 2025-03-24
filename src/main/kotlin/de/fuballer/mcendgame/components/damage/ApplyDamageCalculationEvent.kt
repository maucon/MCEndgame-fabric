package de.fuballer.mcendgame.components.damage

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.components.custom_attributes.data.CustomAttribute
import de.fuballer.mcendgame.components.custom_attributes.data.CustomAttributeType
import de.fuballer.mcendgame.event.Notifier
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageType
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld

data class ApplyDamageCalculationEvent(
    val damager: Entity?,
    val damagerAttributes: Map<CustomAttributeType, List<CustomAttribute>>,

    val damaged: LivingEntity,
    val damagedAttributes: Map<CustomAttributeType, List<CustomAttribute>>,

    val type: DamageType,
    val world: ServerWorld,
//    val isDamageBlocked: Boolean,
    val isDamageCritical: Boolean,

    // === custom properties ===
    val increasedDamage: MutableList<Double> = mutableListOf(),
    val moreDamage: MutableList<Double> = mutableListOf(),

    val increasedAttackDamage: MutableList<Double> = mutableListOf(),
    val moreAttackDamage: MutableList<Double> = mutableListOf(),

    val elementalDamage: MutableList<Double> = mutableListOf(),
    val increasedElementalDamage: MutableList<Double> = mutableListOf(),
    val moreElementalDamage: MutableList<Double> = mutableListOf(),

    val increasedProjectileDamage: MutableList<Double> = mutableListOf(),

    val reducedDamage: MutableList<Double> = mutableListOf(),
    val lessDamage: MutableList<Double> = mutableListOf(),
    val ward: MutableList<Double> = mutableListOf(),
    var dodge: Boolean = false,

    var isExecute: Boolean = false,
) {
    companion object {
        val NOTIFIER = Notifier<ApplyDamageCalculationEvent>()

        fun of(
            damaged: LivingEntity,
            world: ServerWorld,
            source: DamageSource,
            amount: Float,
        ): ApplyDamageCalculationEvent {
            val damager = source.attacker
            val damagerAttributes = (damager as? LivingEntity)?.getAllCustomAttributes() ?: emptyMap()
            val damagedAttributes = damaged.getAllCustomAttributes()
            val damageType = source.type
            val isCritical = isCritical(damager)

            return ApplyDamageCalculationEvent(damager, damagerAttributes, damaged, damagedAttributes, damageType, world, isCritical)
        }

        private fun isCritical(
            entity: Entity?
        ): Boolean {
            val player = entity as? PlayerEntity ?: return false
            val attackCooldown = DamageUtil.getAttackCooldownMultiplier(player)

            if (attackCooldown <= 0.9F) return false
            if (player.fallDistance <= 0.0f) return false
            if (player.isOnGround) return false
            if (player.isClimbing) return false
            if (player.isTouchingWater) return false
            if (player.hasStatusEffect(StatusEffects.BLINDNESS)) return false
            if (player.hasVehicle()) return false
            if (player.isSprinting) return false

            return true
        }
    }
}