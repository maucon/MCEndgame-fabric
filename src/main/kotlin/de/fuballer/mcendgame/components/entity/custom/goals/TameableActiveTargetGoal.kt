package de.fuballer.mcendgame.components.entity.custom.goals

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.Tameable
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.mob.MobEntity

class TameableActiveTargetGoal<S, T>(
    private val tameableMob: S,
    targetClass: Class<T>,
    checkVisibility: Boolean
) : ActiveTargetGoal<T>(tameableMob, targetClass, checkVisibility) where S : MobEntity, S : Tameable, T : LivingEntity {

    override fun canStart(): Boolean {
        if (tameableMob.ownerUuid != null) return false
        return super.canStart()
    }
}