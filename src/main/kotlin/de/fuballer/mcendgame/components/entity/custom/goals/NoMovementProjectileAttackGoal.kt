package de.fuballer.mcendgame.components.entity.custom.goals

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.RangedAttackMob
import net.minecraft.entity.mob.MobEntity
import net.minecraft.util.math.MathHelper
import kotlin.math.sqrt

class NoMovementProjectileAttackGoal<T>(
    private val entity: T,
    private val intervalTicks: Int,
    private val maxShootRange: Float,
    private val initialCooldown: Int = intervalTicks,
) : DisableAbleGoal() where T : MobEntity, T : RangedAttackMob {
    private var targetSeenTicks = 0
    private var cooldown = 0
    private val maxShootRangeSquared = maxShootRange * maxShootRange

    override fun canStart(): Boolean {
        if (isDisabled) return false
        val target: LivingEntity = entity.target ?: return false
        return target.isAlive
    }

    override fun start() {
        cooldown = getTickCount(initialCooldown)
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

        var rangePercentage = sqrt(squaredDistanceToTarget).toFloat() / maxShootRange
        rangePercentage = MathHelper.clamp(rangePercentage, 0.1f, 1.0f)
        entity.shootAt(target, rangePercentage)
    }
}