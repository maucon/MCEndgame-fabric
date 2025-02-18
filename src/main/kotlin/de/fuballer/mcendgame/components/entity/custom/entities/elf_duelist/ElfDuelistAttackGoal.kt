package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.ai.pathing.Path
import net.minecraft.predicate.entity.EntityPredicates
import kotlin.math.max

class ElfDuelistAttackGoal(
    private val mob: ElfDuelistEntity,
    private val moveSpeedFactor: Double = 1.0,
) : Goal() {
    private var path: Path? = null
    private var targetX = 0.0
    private var targetY = 0.0
    private var targetZ = 0.0

    private var updateCountdownTicks = 0

    override fun canStart(): Boolean {
        val target = mob.target ?: return false
        if (!target.isAlive) return false

        path = mob.navigation.findPathTo(target, 0)
        return path != null || mob.isInAttackRange(target)
    }

    override fun shouldContinue(): Boolean {
        val target = mob.target ?: return false
        if (!target.isAlive) return false

        if (!mob.isInWalkTargetRange(target.blockPos)) return false
        return EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(target)
    }

    override fun start() {
        mob.navigation.startMovingAlong(path, moveSpeedFactor)
        mob.isAttacking = true
    }

    override fun stop() {
        mob.target = null
        mob.isAttacking = false
        mob.navigation.stop()
    }

    override fun tick() {
        val target = mob.target ?: return
        update(target)
    }

    private fun update(
        target: LivingEntity
    ) {
        mob.lookControl.lookAt(target, 30.0f, 30.0f)

        updateCountdownTicks = max(updateCountdownTicks - 1, 0)

        if (!shouldUpdate(target)) return

        targetX = target.x
        targetY = target.y
        targetZ = target.z

        updateCountdownTicks = 4 + mob.random.nextInt(7)

        val distance = mob.squaredDistanceTo(target)
        updateCountdownTicks += (distance / 100).toInt()

        if (!mob.navigation.startMovingTo(target, moveSpeedFactor)) {
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
        if (target.squaredDistanceTo(targetX, targetY, targetZ) >= 1) return true

        return mob.random.nextFloat() < 0.05
    }
}