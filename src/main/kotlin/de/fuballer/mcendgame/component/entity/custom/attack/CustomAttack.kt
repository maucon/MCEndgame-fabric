package de.fuballer.mcendgame.component.entity.custom.attack

import de.fuballer.mcendgame.component.entity.custom.interfaces.BlockAbleMovementMob
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import software.bernie.geckolib.animatable.GeoEntity
import kotlin.math.min

open class CustomAttack<T>(
    val startPose: CustomAttackPose,
    val endPose: CustomAttackPose,
    val totalDuration: Int,
    val cooldown: Int,
    private val triggerDistance: Pair<Double, Double>,
    val damage: List<Pair<Int, CustomAttackDamage>>,
    private val animControllerName: String,
    private val animName: String,
    private val blockMovementDuration: Int = 0,
    private val squaredTriggerDistance: Pair<Double, Double> = Pair(triggerDistance.first * triggerDistance.first, triggerDistance.second * triggerDistance.second),
) where T : MobEntity, T : GeoEntity {
    constructor(
        startPose: CustomAttackPose,
        endPose: CustomAttackPose,
        totalDuration: Int,
        cooldown: Int,
        triggerDistance: Pair<Double, Double>,
        damage: Pair<Int, CustomAttackDamage>?,
        animControllerName: String,
        animName: String,
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
        blockMovementDuration,
    )

    open fun canStart(
        attacker: MobEntity,
        target: LivingEntity,
    ): Boolean {
        if (triggerDistance.first < 0) return true
        val squaredDistance = min(attacker.squaredDistanceTo(target), attacker.squaredDistanceTo(target.eyePos))
        return squaredTriggerDistance.first <= squaredDistance && squaredTriggerDistance.second >= squaredDistance
    }

    open fun start(
        attacker: T,
        target: LivingEntity?,
    ) {
        attacker.triggerAnim(animControllerName, animName)

        if (blockMovementDuration == 0) return
        val blockAbleMovementMob = attacker as? BlockAbleMovementMob<*> ?: return
        blockAbleMovementMob.blockMovement(blockMovementDuration)
    }

    fun getDamageInstances(
        target: LivingEntity?,
    ): List<CustomAttackDamageInstance> {
        val instances = mutableListOf<CustomAttackDamageInstance>()
        damage.forEach {
            val damage = it.second
            if (damage.requiresTarget() && target == null) return@forEach

            val damageInstance = CustomAttackDamageInstance(it.first, target, damage)
            instances.add(damageInstance)
        }
        return instances
    }
}