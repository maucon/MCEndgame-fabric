package de.fuballer.mcendgame.components.entity.custom

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import kotlin.math.max

interface AreaDamageDealer {
    fun dealAreaDamage(
        entity: LivingEntity,
        radius: Double,
        facingOffset: Double = 0.0,
        shouldDamage: (LivingEntity) -> Boolean = { it.isAlive },
        damageFactorAtMaxDistance: Float = 0F,
        applyScale: Boolean = true,
        knockbackStrength: Double = 1.0
    ) {
        val world = entity.world as? ServerWorld ?: return

        val scale = if (applyScale) entity.getAttributeValue(EntityAttributes.SCALE) else 1.0
        val scaledRadius = radius * scale
        val scaledKnockbackStrength = knockbackStrength * scale
        val scaledOffset = facingOffset * scale

        val offset = entity.rotationVector.normalize().multiply(scaledOffset)
        val slamCenter = entity.pos.add(offset)

        val box = Box(slamCenter, Vec3d.ZERO).expand(scaledRadius) //TODO is this box even used
        val targets = world.getEntitiesByClass(LivingEntity::class.java, box) { it != entity && shouldDamage(it) }
            .filter { slamCenter.distanceTo(it.pos) <= scaledRadius }

        damageTargets(
            entity,
            targets,
            world,
            slamCenter,
            damageFactorAtMaxDistance,
            scaledRadius,
            scaledKnockbackStrength
        )
    }

    private fun damageTargets(
        entity: LivingEntity,
        targets: List<LivingEntity>,
        world: ServerWorld,
        slamCenter: Vec3d,
        damageFactorAtMaxDistance: Float,
        scaledRadius: Double,
        scaledKnockbackStrength: Double,
    ) {
        val attackDamage = entity.getAttributeValue(EntityAttributes.ATTACK_DAMAGE).toFloat()

        for (target in targets) {
            val distanceVector = target.pos.subtract(slamCenter)
            val distance = distanceVector.length()
            val distancePercent = max(1 - (distance / scaledRadius), 0.0)

            val minDamage = damageFactorAtMaxDistance * attackDamage
            val dmgRange = attackDamage * (1 - damageFactorAtMaxDistance)
            val damage = (minDamage + distancePercent * dmgRange).toFloat()
            target.damage(world, world.damageSources.mobAttack(entity), damage)

            val effectiveKnockbackStrength = scaledKnockbackStrength * distancePercent
            target.takeKnockback(effectiveKnockbackStrength, -distanceVector.x, -distanceVector.z)
        }
    }
}