package de.fuballer.mcendgame.components.entity.custom.goals

import net.minecraft.entity.ai.goal.Goal

open class DisableAbleGoal : Goal() {
    var isDisabled = false
    override fun canStart() = !isDisabled
    override fun shouldContinue() = !isDisabled
}