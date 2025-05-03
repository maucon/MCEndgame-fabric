package de.fuballer.mcendgame.component.entity.custom.attack

import de.fuballer.mcendgame.component.entity.custom.interfaces.BlockAbleMovementMob
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.util.math.Vec3d
import software.bernie.geckolib.animatable.GeoEntity
import kotlin.math.max

class CustomLeapAttack<T>(
    startPose: CustomAttackPose,
    endPose: CustomAttackPose,
    totalDuration: Int,
    cooldown: Int, triggerDistance: Pair<Double, Double>,
    damage: List<Pair<Pair<Int, Int>, CustomAttackDamage>>,
    animControllerName: String,
    animName: String,
    private val leapType: LeapType,
    blockMovementDuration: Int = 0,
) : CustomAttack<T>(startPose, endPose, totalDuration, cooldown, triggerDistance, damage, animControllerName, animName, blockMovementDuration) where T : MobEntity, T : GeoEntity {
    constructor(
        startPose: CustomAttackPose,
        endPose: CustomAttackPose,
        totalDuration: Int,
        cooldown: Int,
        triggerDistance: Pair<Double, Double>,
        damage: Pair<Pair<Int, Int>, CustomAttackDamage>?,
        animControllerName: String,
        animName: String,
        leapType: LeapType,
        blockMovementDuration: Int = 0,
    ) : this(
        startPose,
        endPose,
        totalDuration,
        cooldown,
        triggerDistance,
        if (damage != null) listOf(damage) else listOf(),
        animControllerName,
        animName,
        leapType,
        blockMovementDuration,
    )

    override fun canStart(
        attacker: MobEntity,
        target: LivingEntity,
    ): Boolean {
        if (!super.canStart(attacker, target)) return false
        return true
    }

    override fun start(
        attacker: T,
        target: LivingEntity?
    ) {
        super.start(attacker, target)

        val existingTarget = target ?: return
        attacker.lookAtEntity(existingTarget, 90F, 90F)
        attacker.lookControl.lookAt(existingTarget)
        attacker.bodyYaw = attacker.yaw

        val distanceVector = existingTarget.pos.subtract(attacker.pos)
        val newVelocity = leapType.calculateVelocity(distanceVector)
        attacker.velocityModified = true
        attacker.velocity = newVelocity

        val blockAbleMovementMob = attacker as? BlockAbleMovementMob<*> ?: return
        blockAbleMovementMob.setAirborneBlocked()
    }

    override fun getDamageInstances(
        target: LivingEntity?,
    ): List<CustomAttackDamageInstance> {
        val instances = mutableListOf<CustomLeapAttackDamageInstance>()
        damage.forEach {
            val damage = it.second
            if (damage.requiresTarget() && target == null) return@forEach

            val damageInstance = CustomLeapAttackDamageInstance(it.first, target, damage)
            instances.add(damageInstance)
        }
        return instances
    }

    enum class LeapType(
        private val horizontalDistanceVelocityFactor: ((Double) -> Double),
        private val verticalDistanceVelocityFactor: ((Double) -> Double),
        private val additionalYVelocity: Double,
    ) {
        BASIC(
            { distance -> kotlin.math.abs(distance * 0.2) },
            { distance -> kotlin.math.abs(distance * 0.2) },
            0.3,
        );

        fun calculateVelocity(distanceVector: Vec3d): Vec3d {
            val direction = distanceVector.normalize()

            val horizontalDistance = distanceVector.horizontalLength()
            val horizontalFactor = horizontalDistanceVelocityFactor.invoke(horizontalDistance)
            val verticalDistance = distanceVector.y
            val verticalFactor = verticalDistanceVelocityFactor.invoke(verticalDistance)

            val distanceScaledVelocity = Vec3d(direction.x * horizontalFactor, max(direction.y * verticalFactor, 0.0), direction.z * horizontalFactor)

            val finalVelocity = distanceScaledVelocity.add(0.0, additionalYVelocity, 0.0)
            return finalVelocity
        }
    }
}