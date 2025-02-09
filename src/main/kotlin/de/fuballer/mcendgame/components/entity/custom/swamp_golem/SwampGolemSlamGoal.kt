package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.ai.pathing.Path
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.predicate.entity.EntityPredicates
import java.util.*
import kotlin.math.max

class SwampGolemSlamGoal(
    private val mob: SwampGolemEntity,
    private val speed: Double,
) : Goal() {
    private var path: Path? = null
    private var targetX = 0.0
    private var targetY = 0.0
    private var targetZ = 0.0
    private var updateCountdownTicks = 0
    private var cooldown = 0
    private var lastUpdateTime = 0L
    private var slamTime = -1
    private var frozenYaw = 0F

    companion object {
        private val slamDuration = 25 // 1.25s
        private val slamImpactTime = 17 // 0.85s
    }

    init {
        setControls(EnumSet.of(Control.MOVE, Control.LOOK))
    }

    override fun canStart(): Boolean {
        val time = mob.world.time
        if (time - lastUpdateTime < 20) return false
        lastUpdateTime = time

        val target = mob.target ?: return false
        if (!target.isAlive) return false

        path = mob.navigation.findPathTo(target, 0)
        return path != null || mob.isInAttackRange(target)
    }

    override fun shouldContinue(): Boolean {
        if (isInSlam()) return true

        val target = mob.target ?: return false
        if (!target.isAlive) return false

        if (!mob.isInWalkTargetRange(target.blockPos)) return false
        return target !is PlayerEntity || (!target.isSpectator && !target.isCreative)
    }

    private fun isInSlam() = slamTime >= 0

    override fun start() {
        mob.navigation.startMovingAlong(path, speed)
        mob.isAttacking = true
        updateCountdownTicks = 0
    }

    override fun stop() {
        val target = mob.target
        if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(target)) {
            mob.target = null
        }

        mob.isAttacking = false
        mob.navigation.stop()
    }

    override fun shouldRunEveryTick() = true

    override fun tick() {
        val target = mob.target ?: return
        update(target)
        trySlam(target)
    }

    private fun update(
        target: LivingEntity
    ) {
        if (!updateSlam()) {
            mob.lookControl.lookAt(target, 30.0f, 30.0f)
        }

        cooldown = max(cooldown - 1, 0)
        updateCountdownTicks = max(updateCountdownTicks - 1, 0)

        if (!shouldUpdate(target)) return

        targetX = target.x
        targetY = target.y
        targetZ = target.z

        updateCountdownTicks = 4 + mob.random.nextInt(7)

        val distance = mob.squaredDistanceTo(target)
        updateCountdownTicks += (distance / 100).toInt()

        if (!mob.navigation.startMovingTo(target, speed)) {
            updateCountdownTicks += 15
        }

        updateCountdownTicks = getTickCount(updateCountdownTicks)
    }

    private fun shouldUpdate(
        target: LivingEntity
    ): Boolean {
        if (updateCountdownTicks > 0) return false
        if (!mob.visibilityCache.canSee(target)) return false

        if (targetX == 0.0 && targetY == 0.0 && targetZ == 0.0) return true
        if (target.squaredDistanceTo(targetX, targetY, targetZ) >= 1.0) return true

        return mob.random.nextFloat() < 0.05
    }

    private fun updateSlam(): Boolean {
        if (slamTime < 0) return false

        stopMovement()

        slamTime++

        testSlamDamage()
        if (slamTime < slamDuration) return true

        updateCountdownTicks = getTickCount(5)
        slamTime = -1
        mob.setPose(SwampGolemPose.IDLING)

        return true
    }

    private fun stopMovement() {
        mob.navigation.stop()

        if (slamTime == 0) {
            frozenYaw = mob.yaw
        }
        mob.yaw = frozenYaw
    }

    private fun testSlamDamage() {
        if (slamTime != slamImpactTime) return

        mob.dealAreaDamage(mob, 4.0, 0.8)
    }

    private fun trySlam(target: LivingEntity) {
        if (!canSlam(target)) return

        resetCooldown()
        slamTime = 0
        mob.setPose(SwampGolemPose.SLAMMING)
    }

    private fun resetCooldown() {
        cooldown = getTickCount(80)
    }

    private fun isCooledDown() = cooldown <= 0

    private fun canSlam(target: LivingEntity) =
        isCooledDown() && mob.isInAttackRange(target) && mob.visibilityCache.canSee(target)
}