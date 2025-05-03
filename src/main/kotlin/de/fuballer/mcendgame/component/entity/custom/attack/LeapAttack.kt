package de.fuballer.mcendgame.component.entity.custom.attack

import de.fuballer.mcendgame.component.entity.custom.attack.damage.DelayedAttackDamage
import de.fuballer.mcendgame.component.entity.custom.attack.damage.instance.LeapAttackDamageInstance
import de.fuballer.mcendgame.component.entity.custom.attack.data.AttackAnimationData
import de.fuballer.mcendgame.component.entity.custom.attack.trigger_condition.TriggerCondition
import de.fuballer.mcendgame.component.entity.custom.interfaces.BlockAbleMovementMob
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.util.math.Vec3d
import software.bernie.geckolib.animatable.GeoEntity
import kotlin.math.max

class LeapAttack<T>(
    animationData: AttackAnimationData,
    totalDuration: Int,
    cooldown: Int,
    trigger: TriggerCondition,
    damage: List<DelayedAttackDamage>,
    private val leapType: LeapType,
    blockMovementDuration: Int = 0,
) : Attack<T>(animationData, totalDuration, cooldown, trigger, damage, blockMovementDuration) where T : MobEntity, T : GeoEntity {
    constructor(
        animationData: AttackAnimationData,
        totalDuration: Int,
        cooldown: Int,
        trigger: TriggerCondition,
        damage: DelayedAttackDamage?,
        leapType: LeapType,
        blockMovementDuration: Int = 0,
    ) : this(
        animationData,
        totalDuration,
        cooldown,
        trigger,
        if (damage != null) listOf(damage) else listOf(),
        leapType,
        blockMovementDuration,
    )

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

    override fun getDamageInstance(
        target: LivingEntity?,
        delayedDamage: DelayedAttackDamage
    ) = LeapAttackDamageInstance(delayedDamage.minDelay, delayedDamage.maxDelay, target, delayedDamage.damage)

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