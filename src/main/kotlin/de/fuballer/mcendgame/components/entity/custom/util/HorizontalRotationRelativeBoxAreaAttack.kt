package de.fuballer.mcendgame.components.entity.custom.util

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

class HorizontalRotationRelativeBoxAreaAttack(
    private val forwardRange: Double, // only forward
    private val sideRange: Double, // left & right -> 2* sideRange
    private val heightRange: Double, // up & down -> 2* heightRange
    private val forwardOffset: Double = 0.0, // positive -> forward
    private val sideOffset: Double = 0.0, // positive -> right
    private val heightOffset: Double = 0.0, // positive -> up
    private val applyScale: Boolean = true,
    private val knockbackType: KnockbackType = KnockbackType.DAMAGER_CENTER,
) {
    enum class KnockbackType {
        FACING,
        DAMAGER_CENTER,
        BOX_CENTER,
    }

    fun dealDamage(damager: LivingEntity, damage: Float, knockback: Double) {
        val world = damager.world as? ServerWorld ?: return

        val forward = damager.getRotationVector(damager.pitch, damager.bodyYaw).horizontal.normalize()
        val sideways = forward.crossProduct(Vec3d(0.0, 1.0, 0.0))

        val scale = if (applyScale) damager.getAttributeValue(EntityAttributes.SCALE) else 1.0

        val targets = getTargets(world, damager, scale).filter {
            isInAttackArea(it.pos.subtract(damager.pos), forward, sideways, scale)
                    || isInAttackArea(it.pos.add(0.0, it.height.toDouble(), 0.0).subtract(damager.pos), forward, sideways, scale)
        }

        dealDamage(world, targets, damager, damage, knockback, scale, forward, sideways)
    }

    private fun getTargets(
        world: ServerWorld,
        damager: LivingEntity,
        scale: Double,
    ): List<LivingEntity> {
        val box = getBoundingBox(damager, scale)
        println(box)
        return world.getEntitiesByClass(LivingEntity::class.java, box) { it != damager }
    }

    // returns a Box for all possible targets in range without testing for direction
    private fun getBoundingBox(
        damager: LivingEntity,
        scale: Double,
    ): Box {
        val hD = getMaxHorizontalDistance(scale)
        val x = damager.x
        val y = damager.y + heightOffset
        val z = damager.z
        return Box(x - hD, y - heightRange - 3, z - hD, x + hD, y + heightRange, z + hD) // -3 accounts for height of most mobs
    }

    private fun getMaxHorizontalDistance(scale: Double): Double {
        val maxForward = max(forwardRange + forwardOffset, -forwardOffset)
        val maxSideways = sideRange + abs(sideOffset)
        val distance = sqrt(maxForward * maxForward + maxSideways * maxSideways)
        return distance * if (applyScale) scale else 1.0
    }

    private fun isInAttackArea(
        relativePos: Vec3d,
        forward: Vec3d,
        sideways: Vec3d,
        scale: Double,
    ): Boolean {
        val forwardDistance = relativePos.dotProduct(forward)
        val sidewaysDistance = relativePos.dotProduct(sideways)
        val heightDistance = relativePos.y

        val scaleFactor = if (applyScale) scale else 1.0

        val maxForward = (forwardRange + forwardOffset) * scaleFactor
        val minForward = (forwardOffset) * scaleFactor
        if (forwardDistance > maxForward || forwardDistance < minForward) return false

        val maxSide = (sideRange + sideOffset) * scaleFactor
        val minSide = (-sideRange + sideOffset) * scaleFactor
        if (sidewaysDistance > maxSide || sidewaysDistance < minSide) return false

        val maxHeight = (heightRange + heightOffset) * scaleFactor
        val minHeight = (-heightRange + heightOffset) * scaleFactor
        if (heightDistance > maxHeight || heightDistance < minHeight) return false

        return true
    }

    private fun dealDamage(
        world: ServerWorld,
        targets: List<LivingEntity>,
        damager: LivingEntity,
        damage: Float,
        knockback: Double,
        scale: Double,
        forward: Vec3d,
        sideways: Vec3d
    ) {
        val damageSource = damager.damageSources.mobAttack(damager)
        targets.forEach {
            it.damage(world, damageSource, damage)
            applyKnockback(it, damager, knockback, scale, forward, sideways)
        }
    }

    private fun applyKnockback(
        target: LivingEntity,
        damager: LivingEntity,
        knockback: Double,
        scale: Double,
        forward: Vec3d,
        sideways: Vec3d,
    ) {
        val knockBackStrength = knockback * if (applyScale) scale else 1.0
        target.velocityModified = true

        when (knockbackType) {
            KnockbackType.FACING -> target.takeKnockback(knockBackStrength, -forward.x, -forward.z)

            KnockbackType.BOX_CENTER -> {
                val knockbackCenter = getAttackBoxCenter(damager, scale, forward, sideways)
                val knockbackDirection = target.pos.subtract(knockbackCenter).normalize()
                target.takeKnockback(knockBackStrength, -knockbackDirection.x, -knockbackDirection.z)
            }

            KnockbackType.DAMAGER_CENTER -> {
                val knockbackDirection = target.pos.subtract(damager.pos).normalize()
                target.takeKnockback(knockBackStrength, -knockbackDirection.x, -knockbackDirection.z)
            }
        }
    }

    private fun getAttackBoxCenter(
        damager: LivingEntity,
        scale: Double,
        forward: Vec3d,
        sideways: Vec3d,
    ): Vec3d {
        var center = damager.pos

        val forwardCenter = (forwardOffset + forwardRange / 2) * scale
        center = center.add(forward.multiply(forwardCenter))

        val sidewaysCenter = sideOffset * scale
        center = center.add(sideways.multiply(sidewaysCenter))

        return center
    }
}