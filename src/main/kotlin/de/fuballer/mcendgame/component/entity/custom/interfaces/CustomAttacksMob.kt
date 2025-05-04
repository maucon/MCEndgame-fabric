package de.fuballer.mcendgame.component.entity.custom.interfaces

import de.fuballer.mcendgame.component.entity.custom.attack.Attack
import de.fuballer.mcendgame.component.entity.custom.attack.AttackPose
import de.fuballer.mcendgame.component.entity.custom.attack.damage.instance.AttackDamageInstance
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import software.bernie.geckolib.animatable.GeoEntity

interface CustomAttacksMob<T> where T : MobEntity, T : GeoEntity {
    var attackPose: AttackPose
    var attackDuration: Int

    val attacks: List<RandomOption<out Attack<T>>>

    val attackCooldowns: MutableMap<Attack<T>, Int>

    val attackDamageInstances: MutableList<AttackDamageInstance>

    fun tickAttacks(
        world: ServerWorld,
        damager: T,
    ) {
        tickCooldowns()
        tickAttackDamageInstances(world, damager)

        if (attackDuration > 0) {
            --attackDuration
            return
        }

        if (!canAttack()) return
        if (damager.target?.isAlive == true) return
        if (attackPose == AttackPose.DEFAULT) return
        val resetAttack = getResetAttack() ?: return
        attack(damager, resetAttack)
    }

    fun canAttack() = attackDuration == 0

    fun attack(
        attacker: T,
        attack: Attack<T>,
    ) {
        attackDuration = attack.totalDuration
        attackPose = attack.animationData.endPose

        attackCooldowns[attack] = attack.cooldown

        val target = attacker.target
        attack.start(attacker, target)
        attackDamageInstances.addAll(attack.getDamageInstances(target))
    }

    private fun tickCooldowns() {
        val iterator = attackCooldowns.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            val decCD = entry.value - 1
            if (decCD <= 0) {
                iterator.remove()
                continue
            }
            entry.setValue(decCD)
        }
    }

    private fun tickAttackDamageInstances(
        world: ServerWorld,
        damager: MobEntity,
    ) {
        val toRemove = mutableListOf<AttackDamageInstance>()
        for (attack in attackDamageInstances) {
            if (!attack.tick(world, damager)) continue
            toRemove.add(attack)
        }
        attackDamageInstances.removeAll(toRemove)
    }

    fun getRandomAttack(
        attacker: MobEntity,
        ignoreTriggerConditions: Boolean = false,
    ): Attack<T>? {
        val target = attacker.target
        val possibleAttacks = attacks
            .filter { it.option.animationData.startPose == attackPose }
            .filter { !attackCooldowns.containsKey(it.option) }
            .filter { ignoreTriggerConditions || it.option.canStart(attacker, target) }

        if (possibleAttacks.isNotEmpty()) return RandomUtil.pick(possibleAttacks).option
        return null
    }

    private fun getResetAttack(): Attack<T>? {
        val possibleAttacks = attacks
            .filter { it.option.animationData.startPose == attackPose }
            .filter { it.option.animationData.endPose == AttackPose.DEFAULT }
        if (possibleAttacks.isNotEmpty()) return RandomUtil.pick(possibleAttacks).option
        return null
    }
}