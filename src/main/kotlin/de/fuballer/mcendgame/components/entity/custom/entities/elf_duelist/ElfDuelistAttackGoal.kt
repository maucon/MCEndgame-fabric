package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.ai.pathing.Path
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d
import java.util.*
import kotlin.math.max

class ElfDuelistAttackGoal(
    private val mob: ElfDuelistEntity,
    private val moveSpeedFactor: Double = 1.0,
) : Goal() {
    private var path: Path? = null
    private var targetX = 0.0
    private var targetY = 0.0
    private var targetZ = 0.0

    private var updatePathingCountdownTicks = 0
    private var dealDamageTime = 0

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
        updateAttack(target)
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

    private fun updateAttack(
        target: LivingEntity
    ) {
        updateAttackDamage(target)
        if (!shouldStartNextAttack(target)) return

        val currentPose = mob.dataTracker.get(ElfDuelistEntity.ATTACK_POSE)
        val chosenAttack = ElfDuelistAttackPose.POSE_SEQUENCES[currentPose]?.random() ?: return
        val nextPose = chosenAttack.newPose
        mob.setAttackPose(nextPose)
        dealDamageTime = getTickCount(chosenAttack.damageTime)
    }

    private fun updateAttackDamage(
        target: LivingEntity
    ) {
        if (dealDamageTime == 0) return
        if (--dealDamageTime > 0) return

        dealDamage(target)
    }

    private fun dealDamage(
        target: LivingEntity
    ) {
        val world = mob.world as? ServerWorld ?: return
        if (!isInReach(target)) return

        val attackDamage = mob.getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat()
        target.damage(world, world.damageSources.mobAttack(mob), attackDamage)

        val distanceVector = target.eyePos.subtract(mob.eyePos)
        val attackKnockback = mob.getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK)
        target.takeKnockback(attackKnockback, -distanceVector.x, -distanceVector.z)
    }

    private fun shouldStartNextAttack(
        target: LivingEntity
    ): Boolean {
        if (!isInReach(target)) return false

        val currentAttack = ElfDuelistAttackPose.getAttack(
            mob.dataTracker.get(ElfDuelistEntity.PREVIOUS_ATTACK_POSE),
            mob.dataTracker.get(ElfDuelistEntity.ATTACK_POSE)
        ) ?: return true
        val totalAttackDuration = currentAttack.animationTime + currentAttack.cooldownAfter
        val ticksSinceAttackStart = mob.world.time - mob.dataTracker.get(ElfDuelistEntity.ATTACK_POSE_CHANGED)

        return ticksSinceAttackStart > totalAttackDuration
    }

    private fun isInReach(target: LivingEntity) = mob.eyePos.distanceTo(target.eyePos) < 2.2
            || mob.eyePos.distanceTo(target.pos) < 2.2
}