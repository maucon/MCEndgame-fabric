package de.fuballer.mcendgame.components.entity.custom.attack

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import kotlin.math.min

class CustomBasicAttackDamage(
    damageFactor: Float,
    knockbackFactor: Double,
    private val hitRange: Double,
    private val squaredHitRange: Double = hitRange * hitRange
) : CustomAttackDamage(damageFactor, knockbackFactor) {
    override fun apply(
        world: ServerWorld,
        damager: MobEntity,
        target: LivingEntity?
    ) {
        if (target?.isAlive != true) return
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