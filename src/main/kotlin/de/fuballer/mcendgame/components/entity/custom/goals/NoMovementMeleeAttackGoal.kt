package de.fuballer.mcendgame.components.entity.custom.goals

import de.fuballer.mcendgame.components.entity.custom.interfaces.MeleeAttackMob
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class NoMovementMeleeAttackGoal<T>(
    private val entity: T,
    private val intervalTicks: Int,
    range: Double,
) : DisableAbleGoal() where T : MobEntity, T : MeleeAttackMob {
    private val squaredRange = range * range
    private var cooldown = getTickCount(intervalTicks)

    override fun canStart(): Boolean {
        if (isDisabled) return false
        val target: LivingEntity = entity.target ?: return false
        return target.isAlive
    }

    override fun start() {
        cooldown = getTickCount(intervalTicks)
    }

    override fun shouldContinue() = canStart()

    override fun stop() {}

    override fun tick() {
        if (--cooldown > 0) return
        cooldown = 0

        val target = entity.target ?: return
        val squaredDistanceToTarget = entity.squaredDistanceTo(target)
        if (squaredDistanceToTarget > squaredRange) return
        if (!entity.visibilityCache.canSee(target)) return

        entity.meleeAttack(target)
        cooldown = getTickCount(intervalTicks)
    }
}