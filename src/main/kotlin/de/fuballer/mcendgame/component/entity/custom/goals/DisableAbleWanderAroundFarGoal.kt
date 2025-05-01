package de.fuballer.mcendgame.component.entity.custom.goals

import net.minecraft.entity.ai.goal.WanderAroundFarGoal
import net.minecraft.entity.mob.PathAwareEntity

class DisableAbleWanderAroundFarGoal(
    pathAwareEntity: PathAwareEntity,
    speed: Double,
    probability: Float = 0.001F,
) : WanderAroundFarGoal(pathAwareEntity, speed, probability) {
    var isDisabled = false

    override fun canStart(): Boolean {
        if (isDisabled) return false
        return super.canStart()
    }

    override fun shouldContinue(): Boolean {
        if (isDisabled) return false
        return super.shouldContinue()
    }
}