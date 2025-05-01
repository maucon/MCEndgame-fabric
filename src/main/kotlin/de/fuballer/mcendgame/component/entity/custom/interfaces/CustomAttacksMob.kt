package de.fuballer.mcendgame.component.entity.custom.interfaces

import de.fuballer.mcendgame.component.entity.custom.attack.CustomAttack
import de.fuballer.mcendgame.component.entity.custom.attack.CustomAttackDamageInstance
import de.fuballer.mcendgame.component.entity.custom.attack.CustomAttackPose
import de.fuballer.mcendgame.util.random.RandomOption
import de.fuballer.mcendgame.util.random.RandomUtil
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import software.bernie.geckolib.animatable.GeoEntity
import kotlin.math.min

interface CustomAttacksMob<T> where T : MobEntity, T : GeoEntity {
    var attackPose: CustomAttackPose
    var attackDuration: Int

    val attacks: List<RandomOption<CustomAttack>>

    val attackCooldowns: MutableMap<CustomAttack, Int>

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

        if (damager.target?.isAlive == true) return
        if (attackPose == CustomAttackPose.DEFAULT) return
        val resetAttack = getResetAttack() ?: return
        attack(damager, resetAttack)
    }

    fun canAttack() = attackDuration == 0

    fun attack(
        damager: T,
        attack: CustomAttack,
    ) {
        if (!canAttack()) return
        attackDuration = attack.totalDuration
        attackPose = attack.endPose

        attackCooldowns[attack] = attack.cooldown

        triggerAttackAnimation(damager, attack)

        val target = damager.target
        attack.damage.forEach {
            val damage = it.second
            if (damage.requiresTarget() && target == null) return@forEach

            val damageInstance = CustomAttackDamageInstance(it.first, target, damage)
            attackDamageInstances.add(damageInstance)
        }
    }

    private fun triggerAttackAnimation(
        damager: T,
        attack: CustomAttack,
    ) {
        damager.triggerAnim(attack.animControllerName, attack.animName)
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
            if (!attack.tick()) continue
            attack.apply(world, damager)
            toRemove.add(attack)
        }
        attackDamageInstances.removeAll(toRemove)
    }

    fun getRandomAttack(
        attacker: MobEntity,
    ): CustomAttack? {
        val target = attacker.target ?: return null

        val squaredDistance = min(attacker.squaredDistanceTo(target), attacker.squaredDistanceTo(target.eyePos))

        val possibleAttacks = attacks
            .filter { it.option.startPose == attackPose }
            .filter { (it.option.triggerRange < 0 || it.option.squaredTriggerRange >= squaredDistance) }
            .filter { !attackCooldowns.containsKey(it.option) }
        if (possibleAttacks.isNotEmpty()) return RandomUtil.pick(possibleAttacks).option
        return null
    }

    private fun getResetAttack(): CustomAttack? {
        val possibleAttacks = attacks
            .filter { it.option.startPose == attackPose }
            .filter { it.option.endPose == CustomAttackPose.DEFAULT }
        if (possibleAttacks.isNotEmpty()) return RandomUtil.pick(possibleAttacks).option
        return null
    }
}