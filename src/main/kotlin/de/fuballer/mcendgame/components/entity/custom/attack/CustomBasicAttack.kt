package de.fuballer.mcendgame.components.entity.custom.attack

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import kotlin.math.min

class CustomBasicAttack(
    startPose: CustomAttackPose,
    endPose: CustomAttackPose,
    damageDelay: Int,
    totalDuration: Int,
    hitRange: Double,
    damageFactor: Float,
    knockbackFactor: Double,
    animControllerName: String,
    animName: String,
) : CustomAttack(startPose, endPose, damageDelay, totalDuration, hitRange, damageFactor, knockbackFactor, animControllerName, animName) {
    override fun apply(
        world: ServerWorld,
        damager: MobEntity,
        target: LivingEntity
    ) {
        if (!target.isAlive) return
        val squaredDistance = min(damager.squaredDistanceTo(target), damager.squaredDistanceTo(target.eyePos))
        if (squaredDistance > squaredHitRange) return

        val damage = getDamage(damager)
        val damageSource = damager.damageSources.mobAttack(damager)
        target.damage(world, damageSource, damage)

        val knockback = getKnockback(damager)
        val knockbackDirection = target.pos.subtract(damager.pos).normalize()
        target.takeKnockback(knockback, -knockbackDirection.x, -knockbackDirection.z)
    }
}