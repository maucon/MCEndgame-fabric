package de.fuballer.mcendgame.component.entity.custom.attack.trigger_condition

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class HorizontalDistanceTriggerCondition(
    private val minHorizontalDistance: Double,
    private val maxHorizontalDistance: Double,
    private val squaredMinHorizontalDistance: Double = minHorizontalDistance * minHorizontalDistance,
    private val squaredMaxHorizontalDistance: Double = maxHorizontalDistance * maxHorizontalDistance,
) : TriggerCondition() {
    constructor(maxHorizontalDistance: Double) : this(0.0, maxHorizontalDistance)

    override fun doesTrigger(
        attacker: MobEntity,
        target: LivingEntity
    ) = target.pos.subtract(attacker.pos).horizontalLengthSquared() in squaredMinHorizontalDistance..squaredMaxHorizontalDistance
}