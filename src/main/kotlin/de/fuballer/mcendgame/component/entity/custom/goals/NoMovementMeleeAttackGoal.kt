package de.fuballer.mcendgame.component.entity.custom.goals

import de.fuballer.mcendgame.component.entity.custom.interfaces.MeleeAttackMob
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class NoMovementMeleeAttackGoal<T>(
    private val entity: T,
    private val intervalTicks: Int,
    range: Double,
    private val initialCooldown: Int = intervalTicks,
) : DisableAbleGoal() where T : MobEntity, T : MeleeAttackMob {
    private var squaredRange = range * range
    private var cooldown = 0

    override fun canStart(): Boolean {
        if (isDisabled) return false
        val target: LivingEntity = entity.target ?: return false
        return target.isAlive
    }

    override fun start() {
        cooldown = getTickCount(initialCooldown)
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

    fun setRange(range: Double) {
        squaredRange = range * range
    }
}