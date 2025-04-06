package de.fuballer.mcendgame.components.entity.custom.goals

import de.fuballer.mcendgame.components.entity.custom.interfaces.HookAttackMob
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.mob.MobEntity

class HookAttackGoal<T>(
    private val entity: T,
    private val intervalTicks: Int,
    private val maxShootRange: Float,
) : Goal() where T : MobEntity, T : HookAttackMob {
    private var targetSeenTicks = 0
    private var cooldown = 0
    private val maxShootRangeSquared = maxShootRange * maxShootRange

    override fun canStart(): Boolean {
        val target: LivingEntity = entity.target ?: return false
        return target.isAlive
    }

    override fun start() {
        cooldown = getTickCount(intervalTicks)
    }

    override fun shouldContinue() = canStart()

    override fun stop() {
        targetSeenTicks = 0
    }

    override fun tick() {
        val target = entity.target

        val canSeeTarget = entity.visibilityCache.canSee(target)
        targetSeenTicks = if (canSeeTarget) targetSeenTicks + 1 else 0

        val squaredDistanceToTarget = entity.squaredDistanceTo(target)
        if (squaredDistanceToTarget > maxShootRangeSquared) return

        if (--cooldown > 0) return
        cooldown = getTickCount(intervalTicks)

        if (!canSeeTarget) return

        entity.shootHookAt(target!!)
    }
}