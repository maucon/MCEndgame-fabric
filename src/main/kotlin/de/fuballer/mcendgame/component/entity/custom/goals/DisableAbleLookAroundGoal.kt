package de.fuballer.mcendgame.component.entity.custom.goals

import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.mob.MobEntity

class DisableAbleLookAroundGoal(
    entity: MobEntity,
) : LookAroundGoal(entity) {
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