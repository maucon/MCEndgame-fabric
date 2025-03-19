package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.ai.pathing.Path
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.math.Vec3d
import java.util.*
import kotlin.math.max

class ElfDuelistPathToTargetGoal(
    private val mob: ElfDuelistEntity,
    private val moveSpeedFactor: Double = 1.0,
) : Goal() {
    private var path: Path? = null
    private var targetX = 0.0
    private var targetY = 0.0
    private var targetZ = 0.0

    private var updatePathingCountdownTicks = 0

    init {
        setControls(EnumSet.of(Control.MOVE, Control.LOOK))
    }

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
        updatePathing(target)
    }

    private fun updatePathing(
        target: LivingEntity
    ) {
        updatePathingCountdownTicks = max(updatePathingCountdownTicks - 1, 0)

        if (!shouldUpdatePathing(target)) return

        targetX = target.x
        targetY = target.y
        targetZ = target.z

        updatePathingCountdownTicks = 4 + mob.random.nextInt(4) + (mob.distanceTo(target) / 10).toInt()

        if (!mob.navigation.startMovingTo(target, moveSpeedFactor)) {
            updatePathingCountdownTicks += 10
        }

        updatePathingCountdownTicks = getTickCount(updatePathingCountdownTicks)
    }

    private fun shouldUpdatePathing(
        target: LivingEntity
    ): Boolean {
        if (!mob.visibilityCache.canSee(target)) return false

        if (updatePathingCountdownTicks == 0) return true

        if (targetX == 0.0 && targetY == 0.0 && targetZ == 0.0) return true
        if (target.pos.distanceTo(Vec3d(targetX, targetY, targetZ)) > 1) return true
        if (mob.navigation.isIdle && mob.distanceTo(target) > 1.8) return true

        return mob.random.nextFloat() < 0.05
    }
}