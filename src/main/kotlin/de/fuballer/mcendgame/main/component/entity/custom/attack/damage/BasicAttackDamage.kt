package de.fuballer.mcendgame.main.component.entity.custom.attack.damage

import de.fuballer.mcendgame.main.component.custom_attribute.effects.knockback.AttackKnockbackUtil.takeKnockbackFrom
import de.fuballer.mcendgame.main.component.damage.dealing.DamageDealingService.dealGenericAttackDamage
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld
import kotlin.math.min

class BasicAttackDamage(
    damageFactor: Float,
    knockbackFactor: Double,
    private val hitRange: Double,
    private val squaredHitRange: Double = hitRange * hitRange
) : AttackDamage(damageFactor, knockbackFactor) {
    override fun apply(
        world: ServerWorld,
        damager: MobEntity,
        target: LivingEntity?
    ): Boolean {
        if (target?.isAlive != true) return false
        val squaredDistance = min(damager.squaredDistanceTo(target), damager.squaredDistanceTo(target.eyePos))
        if (squaredDistance > squaredHitRange) return false

        val damage = getDamage(damager)
        target.dealGenericAttackDamage(damage, damager)

        val knockback = getKnockback(damager)
        val knockbackDirection = target.entityPos.subtract(damager.entityPos).normalize()
        target.takeKnockbackFrom(damager, knockback, -knockbackDirection.x, -knockbackDirection.z)

        return true
    }
}