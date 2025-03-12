package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.server.world.ServerWorld

class ElfDuelistMeleeAttackGoal(
    private val mob: ElfDuelistEntity,
) : Goal() {
    private var dealDamageTime = 0

    override fun canStart(): Boolean {
        val target = mob.target ?: return false
        return target.isAlive
    }

    override fun shouldContinue(): Boolean {
        val target = mob.target ?: return false
        if (!target.isAlive) return false

        return EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(target)
    }

    override fun tick() {
        val target = mob.target ?: return
        updateAttack(target)
    }

    private fun updateAttack(
        target: LivingEntity
    ) {
        updateAttackDamage(target)
        if (!mob.isPreviousAttackDone()) return

        val currentPose = mob.dataTracker.get(ElfDuelistEntity.ATTACK_POSE)
        val chosenAttack = ElfDuelistAttackPose.POSE_SEQUENCES[currentPose]?.random() ?: return
        val nextPose = if (isInReach(target)) chosenAttack.newPose else ElfDuelistAttackPose.DEFAULT
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

    private fun isInReach(target: LivingEntity) = target.isAlive && (mob.eyePos.distanceTo(target.eyePos) < 2.2
            || mob.eyePos.distanceTo(target.pos) < 2.2)
}