package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.configuration.RuntimeConfig
import de.fuballer.mcendgame.main.messaging.misc.EntityShotArrowEvent
import de.fuballer.mcendgame.main.util.extension.mixin.PersistentProjectileEntityMixinExtension.hasLoadBeenProcessed
import de.fuballer.mcendgame.main.util.extension.mixin.PersistentProjectileEntityMixinExtension.isAdditional
import de.fuballer.mcendgame.main.util.extension.mixin.PersistentProjectileEntityMixinExtension.setIsAdditional
import de.fuballer.mcendgame.main.util.extension.mixin.PersistentProjectileEntityMixinExtension.setLoadProcessed
import de.fuballer.mcendgame.main.util.extension.mixin.PersistentProjectileEntityMixinExtension.setPierceLevel
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

private const val ROTATION_PER_ARROW = 5.0
private const val DEFAULT_ROTATION_PITCH_THRESHOLD = 85

@Injectable
class AdditionalArrowsService {
    @EventSubscriber
    fun on(event: EntityShotArrowEvent) {
        val originalArrow = event.arrow
        if (originalArrow.hasLoadBeenProcessed()) return
        if (originalArrow.isAdditional()) return
        originalArrow.setLoadProcessed()

        val attributes = event.owner.getAllCustomAttributes()[CustomAttributeTypes.ADDITIONAL_ARROWS] ?: return
        val additionalArrows = attributes.sumOf { it.rolls[0].asIntRoll().getValue() }

        shootAdditionalArrows(originalArrow, event.owner, additionalArrows)
    }

    private fun shootAdditionalArrows(
        original: PersistentProjectileEntity,
        owner: LivingEntity,
        count: Int,
    ) {
        if (count <= 0) return
        val world = original.entityWorld as? ServerWorld ?: return

        val originalVelocity = original.velocity
        val rotationVector = getRotationVector(originalVelocity, owner.pitch, owner.yaw)

        var rotation = -ROTATION_PER_ARROW * (count / 2.0)
        val newArrows = mutableListOf<ArrowEntity>()
        repeat(count + 1) {
            val arrow = ArrowEntity(EntityType.ARROW, world)

            arrow.owner = owner
            arrow.setPosition(original.entityPos)
            arrow.yaw = original.yaw
            arrow.pitch = original.pitch
            arrow.velocity = getRotatedAroundAxis(originalVelocity, rotationVector, rotation)
            arrow.velocityDirty = true
            arrow.isCritical = original.isCritical
            arrow.isOnFire = original.isOnFire
            arrow.fireTicks = original.fireTicks
            arrow.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY
            arrow.setPierceLevel(original.pierceLevel)
            arrow.setIsAdditional()

            newArrows.add(arrow)

            rotation += ROTATION_PER_ARROW
        }

        original.remove(Entity.RemovalReason.DISCARDED)

        RuntimeConfig.SERVER.execute { newArrows.forEach { world.spawnEntity(it) } }
    }

    private fun getRotationVector(velocity: Vec3d, shooterPitch: Float, shooterYaw: Float): Vec3d {
        if (abs(shooterPitch) > DEFAULT_ROTATION_PITCH_THRESHOLD) return getHorizontalVectorFromYaw(shooterYaw)
        if (velocity.y == 0.0) return Vec3d(0.0, 1.0, 0.0)

        val newY = -(velocity.x * velocity.x + velocity.z * velocity.z) / velocity.y
        return Vec3d(velocity.x, newY, velocity.z)
    }

    private fun getHorizontalVectorFromYaw(yaw: Float): Vec3d {
        val radians = Math.toRadians(yaw.toDouble())
        val x = -sin(radians)
        val z = cos(radians)
        return Vec3d(x, 0.0, z).normalize()
    }

    fun getRotatedAroundAxis(vector: Vec3d, axis: Vec3d, degrees: Double): Vec3d {
        val radians = Math.toRadians(degrees)
        val normAxis = axis.normalize()
        val cos = cos(radians)
        val sin = sin(radians)

        val term1 = vector.multiply(cos)
        val term2 = normAxis.crossProduct(vector).multiply(sin)
        val term3 = normAxis.multiply(normAxis.dotProduct(vector) * (1 - cos))

        return term1.add(term2).add(term3)
    }
}