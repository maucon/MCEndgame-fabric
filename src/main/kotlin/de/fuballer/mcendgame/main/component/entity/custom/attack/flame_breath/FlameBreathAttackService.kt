package de.fuballer.mcendgame.main.component.entity.custom.attack.flame_breath

import de.fuballer.mcendgame.main.component.damage.dealing.DamageDealingService.dealGenericAttackDamage
import de.fuballer.mcendgame.main.component.particle.HorizontalFlameBreathParticleEffect
import de.fuballer.mcendgame.main.functional.scheduler.Scheduler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min

private val HORIZONTAL_VECTOR = Vec3d(1.0, 0.0, 1.0)

@Injectable
class FlameBreathAttackService(
    private val scheduler: Scheduler,
) {
    @EventSubscriber(sync = true)
    fun on(event: FlameBreathAttackEvent) {
        val attacker = event.attacker
        val world = event.attacker.entityWorld as? ServerWorld ?: return

        val direction = getDirection(attacker, event.target)
        val horizontalDirection = direction.multiply(HORIZONTAL_VECTOR).normalize()
        val horizontalOffset = horizontalDirection.multiply(attacker.width * event.entityWidthOffsetFactor)
        val verticalOffset = Vec3d(0.0, attacker.height * event.entityHeightOffsetFactor, 0.0)
        val originPoint = attacker.entityPos.add(horizontalOffset).add(verticalOffset)

        createParticles(world, attacker, originPoint, horizontalDirection, event.delay, event.duration, event.angle)
        dealDamage(world, attacker, originPoint, horizontalDirection, event.delay, event.duration, event.angle)
    }

    private fun getDirection(
        attacker: Entity,
        target: Entity?,
    ): Vec3d {
        if (target == null) return attacker.rotationVector.multiply(HORIZONTAL_VECTOR).normalize()
        return target.entityPos.subtract(attacker.entityPos).multiply(HORIZONTAL_VECTOR).normalize()
    }

    private fun createParticles(
        world: ServerWorld,
        attacker: Entity,
        originPoint: Vec3d,
        direction: Vec3d,
        delay: Int,
        duration: Int,
        angle: Double,
    ) {
        scheduler.repeatingForDuration(delay, 1, duration) {
            if (!attacker.isAlive) return@repeatingForDuration
            world.spawnParticles(
                HorizontalFlameBreathParticleEffect(direction.x, direction.y, direction.z, angle),
                originPoint.x,
                originPoint.y,
                originPoint.z,
                4,
                0.0,
                0.0,
                0.0,
                1.0,
            )
        }
    }

    private fun dealDamage(
        world: ServerWorld,
        attacker: Entity,
        originPoint: Vec3d,
        direction: Vec3d,
        delay: Int,
        duration: Int,
        angle: Double,
    ) {
        val speed = 0.25

        val halfAngleRad = Math.toRadians(angle / 2.0)
        val cosThreshold = cos(halfAngleRad)

        scheduler.repeatingForDuration(delay, 2, duration + 40) { tick ->
            if (tick == 0) return@repeatingForDuration

            val maxDistance = speed * min(tick, 40) // 40 = particle max age
            val maxDistanceSquared = maxDistance * maxDistance

            var ticksSinceStop = if (tick > duration) (tick - duration) else 0
            if (!attacker.isAlive && attacker is LivingEntity) {
                ticksSinceStop = max(attacker.deathTime, ticksSinceStop)
            }
            val minDistance = speed * ticksSinceStop
            val minDistanceSquared = minDistance * minDistance

            val box = Box(
                originPoint.x - maxDistance,
                originPoint.y - 1.0,
                originPoint.z - maxDistance,
                originPoint.x + maxDistance,
                originPoint.y + 1.0,
                originPoint.z + maxDistance
            )

            val entities = world.getEntitiesByClass(LivingEntity::class.java, box) {
                val squaredDistance = it.squaredDistanceTo(originPoint)
                it != attacker && squaredDistance <= maxDistanceSquared && squaredDistance >= minDistanceSquared
            }

            for (entity in entities) {
                val directionVectorToEntity = entity.entityPos.subtract(originPoint).multiply(HORIZONTAL_VECTOR).normalize()

                val dotProduct = direction.dotProduct(directionVectorToEntity)
                if (dotProduct >= cosThreshold) {
                    entity.dealGenericAttackDamage(1F, attacker) // TODO deal generic elemental damage
                    entity.setOnFireForTicks(80)
                }
            }
        }
    }
}