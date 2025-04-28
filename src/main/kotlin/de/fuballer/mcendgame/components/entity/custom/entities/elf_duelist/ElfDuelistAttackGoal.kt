package de.fuballer.mcendgame.components.entity.custom.entities.elf_duelist

import de.fuballer.mcendgame.components.entity.custom.goals.DisableAbleGoal
import kotlin.math.min

class ElfDuelistAttackGoal(
    private val elfDuelist: ElfDuelistEntity,
    private val attacks: List<ElfDuelistEntity.Attack>,
) : DisableAbleGoal() {
    override fun canStart(): Boolean {
        if (!super.canStart()) return false
        val target = elfDuelist.target ?: return false
        return target.isAlive
    }

    override fun shouldContinue() = canStart()

    override fun tick() {
        if (!elfDuelist.canAttack()) return
        tryAttack()
    }

    private fun tryAttack() {
        val attacks = getPossibleAttacks()
        if (attacks.isEmpty()) return

        val attack = attacks.random()
        elfDuelist.attack(attack)
    }

    private fun getPossibleAttacks(): List<ElfDuelistEntity.Attack> {
        val pose = elfDuelist.getAttackPose()

        val target = elfDuelist.target ?: return listOf()
        val squaredDistance = min(elfDuelist.squaredDistanceTo(target), elfDuelist.squaredDistanceTo(target.eyePos))

        return attacks.filter { it.startPose == pose && (it.hitRange < 0 || it.squaredHitRange >= squaredDistance) }
    }
}