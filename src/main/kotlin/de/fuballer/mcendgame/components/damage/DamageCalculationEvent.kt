package de.fuballer.mcendgame.components.damage

import de.fuballer.mcendgame.components.custom_attributes.data.CustomAttribute
import de.fuballer.mcendgame.components.custom_attributes.data.CustomAttributeType
import de.fuballer.mcendgame.event.Notifier
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageType
import net.minecraft.server.world.ServerWorld

data class DamageCalculationEvent(
    val damager: LivingEntity,
    val damagerAttributes: Map<CustomAttributeType, List<CustomAttribute>>,

    val damaged: LivingEntity,
    val damagedAttributes: Map<CustomAttributeType, List<CustomAttribute>>,

    val type: DamageType,
    val world: ServerWorld,
    val isDungeonWorld: Boolean,
    val isTrialWorld: Boolean,
    val isDamageBlocked: Boolean,
    val isDamageCritical: Boolean,

    // === custom properties ===
    val increasedDamage: MutableList<Double> = mutableListOf(),
    val moreDamage: MutableList<Double> = mutableListOf(),

    val reducedDamage: MutableList<Double> = mutableListOf(),
    val lessDamage: MutableList<Double> = mutableListOf(),

    var isExecute: Boolean = false,
) {
    companion object {
        val NOTIFIER = Notifier<DamageCalculationEvent>()
    }

    fun getFinalDamage(): Double {
        return 5.0 //TODO
    }
}