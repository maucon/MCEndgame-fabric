package de.fuballer.mcendgame.component.entity.custom.attack

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld

abstract class CustomAttackDamage(
    private val damageFactor: Float,
    private val knockbackFactor: Double,
) {
    abstract fun apply(world: ServerWorld, damager: MobEntity, target: LivingEntity?): Boolean

    fun getDamage(damager: MobEntity) = damager.getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat() * damageFactor
    fun getKnockback(damager: MobEntity) = damager.getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK).toFloat() * knockbackFactor

    open fun requiresTarget() = true
}