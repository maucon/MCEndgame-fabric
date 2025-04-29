package de.fuballer.mcendgame.components.entity.custom.attack

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld

abstract class CustomAttack(
    val startPose: CustomAttackPose,
    val endPose: CustomAttackPose,
    val damageDelay: Int,
    val totalDuration: Int,
    val hitRange: Double,
    val damageFactor: Float,
    val knockbackFactor: Double,
    val animControllerName: String,
    val animName: String,
    val squaredHitRange: Double = hitRange * hitRange,
) {
    abstract fun apply(world: ServerWorld, damager: MobEntity, target: LivingEntity)

    protected fun getDamage(damager: MobEntity) = damager.getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat() * damageFactor

    protected fun getKnockback(damager: MobEntity) = damager.getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK).toFloat() * knockbackFactor
}