package de.fuballer.mcendgame.component.entity.custom.attack.trigger_condition

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class DistanceTriggerCondition(
    private val minDistance: Double,
    private val maxDistance: Double,
    private val squaredMinDistance: Double = minDistance * minDistance,
    private val squaredMaxDistance: Double = maxDistance * maxDistance,
) : TriggerCondition() {
    constructor(maxDistance: Double) : this(0.0, maxDistance)

    override fun doesTrigger(
        attacker: MobEntity,
        target: LivingEntity?,
    ): Boolean {
        if (target == null) return false
        return attacker.squaredDistanceTo(target) in squaredMinDistance..squaredMaxDistance
    }
}