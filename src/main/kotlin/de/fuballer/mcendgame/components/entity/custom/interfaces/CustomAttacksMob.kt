package de.fuballer.mcendgame.components.entity.custom.interfaces

import de.fuballer.mcendgame.components.entity.custom.util.AttackDamageInstance
import de.fuballer.mcendgame.components.entity.custom.util.CustomAttack
import de.fuballer.mcendgame.components.entity.custom.util.CustomAttackPose
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import software.bernie.geckolib.animatable.GeoEntity
import kotlin.math.min

interface CustomAttacksMob<T> where T : MobEntity, T : GeoEntity {
    var attackPose: CustomAttackPose
    var attackDuration: Int

    val attacks: List<CustomAttack>
    val resetAttacks: List<CustomAttack>

    val attackDamageInstances: MutableList<AttackDamageInstance>

    fun tickAttacks(
        world: ServerWorld,
        damager: T,
    ) {
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

        triggerAnimation(damager, attack)

        if (attack.damageDelay < 0) return

        val existingTarget = damager.target ?: return
        val damage = damager.getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat() * attack.damageFactor
        val knockback = damager.getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK) * attack.knockbackFactor

        val damageInstance = AttackDamageInstance(attack.damageDelay, existingTarget, attack.squaredHitRange, damage, knockback)
        attackDamageInstances.add(damageInstance)
    }

    private fun triggerAnimation(
        damager: T,
        attack: CustomAttack,
    ) {
        damager.triggerAnim(attack.animControllerName, attack.animName)
    }

    private fun tickAttackDamageInstances(
        world: ServerWorld,
        damager: MobEntity,
    ) {
        val toRemove = mutableListOf<AttackDamageInstance>()
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

        val possibleAttacks = attacks.filter { it.startPose == attackPose && (it.hitRange < 0 || it.squaredHitRange >= squaredDistance) }
        if (possibleAttacks.isNotEmpty()) return possibleAttacks.random()

        return getResetAttack()
    }

    private fun getResetAttack(): CustomAttack? {
        val possibleAttacks = resetAttacks.filter { it.startPose == attackPose }
        if (possibleAttacks.isNotEmpty()) return possibleAttacks.random()
        return null
    }
}