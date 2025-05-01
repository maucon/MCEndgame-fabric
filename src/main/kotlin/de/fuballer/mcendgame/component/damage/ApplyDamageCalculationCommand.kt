package de.fuballer.mcendgame.component.damage

import de.fuballer.mcendgame.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.component.custom_attribute.data.CustomAttributeType
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageType
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.server.world.ServerWorld

data class ApplyDamageCalculationCommand(
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
        fun of(
            damaged: LivingEntity,
            world: ServerWorld,
            source: DamageSource,
        ): ApplyDamageCalculationCommand {
            val damager = source.attacker
            val damagerAttributes = (damager as? LivingEntity)?.getAllCustomAttributes() ?: emptyMap()
            val damagedAttributes = damaged.getAllCustomAttributes()
            val damageType = source.type

            val isProjectileCritical = (source.source as? PersistentProjectileEntity)?.isCritical ?: false
            val isCritical = PlayerAccessUtil.getIsCritical(damager) || isProjectileCritical

            return ApplyDamageCalculationCommand(damager, damagerAttributes, damaged, damagedAttributes, damageType, world, isCritical)
        }
    }
}