package de.fuballer.mcendgame.components.entity.custom.util

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import kotlin.math.min

class AttackDamageInstance(
    private var delay: Int,
    private val target: LivingEntity,
    private val squaredRange: Double,
    private val damage: Float,
    private val knockback: Double,
) {
    fun tick() = --delay == 0

    fun apply(world: ServerWorld, damager: MobEntity) {
        if (!target.isAlive) return
        val squaredDistance = min(damager.squaredDistanceTo(target), damager.squaredDistanceTo(target.eyePos))
        if (squaredDistance > squaredRange) return

        val damageSource = damager.damageSources.mobAttack(damager)
        target.damage(world, damageSource, damage)

        val knockbackDirection = target.pos.subtract(damager.pos).normalize()
        target.takeKnockback(knockback, -knockbackDirection.x, -knockbackDirection.z)
    }
}