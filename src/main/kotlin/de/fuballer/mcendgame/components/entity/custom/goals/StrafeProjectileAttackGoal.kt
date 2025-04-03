package de.fuballer.mcendgame.components.entity.custom.goals

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.RangedAttackMob
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.mob.MobEntity
import net.minecraft.util.math.MathHelper
import java.util.*
import kotlin.math.sqrt

class StrafeProjectileAttackGoal<T>(
    private val entity: T,
    private val speed: Double,
    private val attackInterval: Int,
    private val range: Float,
) : Goal() where  T : RangedAttackMob, T : MobEntity {
    private val squaredRange: Float
    private var cooldown = -1
    private var seeingTargetTicker = 0
    private var movingToLeft = false
    private var backward = false
    private var strafeDirChangeCounter = -1

    init {
        controls = EnumSet.of(Control.MOVE, Control.LOOK)

        squaredRange = range * range
    }

    override fun canStart() = entity.target != null

    override fun shouldContinue(): Boolean {
        if (entity.target != null) return true
        return !entity.navigation.isIdle
    }

    override fun start() {
        super.start()
        entity.setAttacking(true)
    }

    override fun stop() {
        super.stop()
        entity.setAttacking(false)
        seeingTargetTicker = 0
        cooldown = -1
        entity.navigation.stop()
        entity.moveControl.strafeTo(0F, 0F)
    }

    override fun shouldRunEveryTick() = true

    override fun tick() {
        val target = entity.target ?: return

        val canSeeTarget = updateSeeingTargetTicker(target)
        val squaredDistanceToTarget = entity.squaredDistanceTo(target)
        if (squaredDistanceToTarget <= squaredRange && seeingTargetTicker >= 20) {
            entity.navigation.stop()
            strafeDirChangeCounter++
        } else {
            entity.navigation.startMovingTo(target, speed)
            strafeDirChangeCounter = -1
        }

        updateStrafing(target, squaredDistanceToTarget)

        if (--cooldown > 0 || !canSeeTarget) return
        cooldown = getTickCount(attackInterval)

        var distanceRangePercentage: Float = sqrt(squaredDistanceToTarget).toFloat() / range
        distanceRangePercentage = MathHelper.clamp(distanceRangePercentage, 0.1f, 1.0f)
        entity.shootAt(target, distanceRangePercentage)
    }

    private fun updateSeeingTargetTicker(
        target: LivingEntity
    ): Boolean {
        val canSeeTarget = entity.visibilityCache.canSee(target)
        val sawTarget = seeingTargetTicker > 0

        if (canSeeTarget != sawTarget) seeingTargetTicker = 0
        seeingTargetTicker += if (canSeeTarget) 1 else -1

        return canSeeTarget
    }

    private fun updateStrafing(
        target: LivingEntity,
        squaredDistanceToTarget: Double,
    ) {
        if (strafeDirChangeCounter < 0) {
            entity.lookControl.lookAt(target, 30.0f, 30.0f)
            return
        }

        if (strafeDirChangeCounter >= 20) {
            if (entity.random.nextDouble() < 0.3) movingToLeft = !movingToLeft
            if (entity.random.nextDouble() < 0.3) backward = !backward
            strafeDirChangeCounter = 0
        }

        if (squaredDistanceToTarget > squaredRange * 0.75f) {
            backward = false
        } else if (squaredDistanceToTarget < squaredRange * 0.25f) {
            backward = true
        }

        val strafeFB = if (backward) -0.5f else 0.5f
        val strafeLR = if (movingToLeft) 0.5f else -0.5f
        entity.moveControl.strafeTo(strafeFB, strafeLR)
        (entity.controllingVehicle as? MobEntity)?.lookAtEntity(target, 30.0F, 30.0F)

        entity.lookAtEntity(target, 30.0f, 30.0f)
    }
}