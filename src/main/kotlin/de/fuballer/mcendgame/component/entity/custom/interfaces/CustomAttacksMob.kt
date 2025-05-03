package de.fuballer.mcendgame.component.entity.custom.interfaces

import de.fuballer.mcendgame.component.entity.custom.attack.CustomAttack
import de.fuballer.mcendgame.component.entity.custom.attack.CustomAttackDamageInstance
import de.fuballer.mcendgame.component.entity.custom.attack.CustomAttackPose
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import software.bernie.geckolib.animatable.GeoEntity

interface CustomAttacksMob<T> where T : MobEntity, T : GeoEntity {
    var attackPose: CustomAttackPose
    var attackDuration: Int

    val attacks: List<RandomOption<out CustomAttack<T>>>

    val attackCooldowns: MutableMap<CustomAttack<T>, Int>

    val attackDamageInstances: MutableList<CustomAttackDamageInstance>

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
        if (attackPose == CustomAttackPose.DEFAULT) return
        val resetAttack = getResetAttack() ?: return
        attack(damager, resetAttack)
    }

    fun canAttack() = attackDuration == 0

    fun attack(
        attacker: T,
        attack: CustomAttack<T>,
    ) {
        attackDuration = attack.totalDuration
        attackPose = attack.endPose

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
        val toRemove = mutableListOf<CustomAttackDamageInstance>()
        for (attack in attackDamageInstances) {
            if (!attack.tick(world, damager)) continue
            toRemove.add(attack)
        }
        attackDamageInstances.removeAll(toRemove)
    }

    fun getRandomAttack(
        attacker: MobEntity,
    ): CustomAttack<T>? {
        val target = attacker.target ?: return null

        val possibleAttacks = attacks
            .filter { it.option.startPose == attackPose }
            .filter { !attackCooldowns.containsKey(it.option) }
            .filter { it.option.canStart(attacker, target) }
        if (possibleAttacks.isNotEmpty()) return RandomUtil.pick(possibleAttacks).option
        return null
    }

    private fun getResetAttack(): CustomAttack<T>? {
        val possibleAttacks = attacks
            .filter { it.option.startPose == attackPose }
            .filter { it.option.endPose == CustomAttackPose.DEFAULT }
        if (possibleAttacks.isNotEmpty()) return RandomUtil.pick(possibleAttacks).option
        return null
    }
}