package de.fuballer.mcendgame.components.entity.custom.goals

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.mob.MobEntity

class DisableAbleLookAtEntityGoal(
    entity: MobEntity,
    type: Class<out LivingEntity>,
    range: Float,
) : LookAtEntityGoal(entity, type, range) {
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