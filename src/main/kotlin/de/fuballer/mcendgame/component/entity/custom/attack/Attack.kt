package de.fuballer.mcendgame.component.entity.custom.attack

import de.fuballer.mcendgame.component.entity.custom.attack.damage.AttackDamage
import de.fuballer.mcendgame.component.entity.custom.attack.damage.instance.AttackDamageInstance
import de.fuballer.mcendgame.component.entity.custom.attack.trigger_condition.TriggerCondition
import de.fuballer.mcendgame.component.entity.custom.interfaces.BlockAbleMovementMob
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import software.bernie.geckolib.animatable.GeoEntity

open class Attack<T>(
    val startPose: AttackPose,
    val endPose: AttackPose,
    val totalDuration: Int,
    val cooldown: Int,
    private val trigger: TriggerCondition,
    val damage: List<Pair<Pair<Int, Int>, AttackDamage>>,
    private val animControllerName: String,
    private val animName: String,
    private val blockMovementDuration: Int = 0,
) where T : MobEntity, T : GeoEntity {
    constructor(
        startPose: AttackPose,
        endPose: AttackPose,
        totalDuration: Int,
        cooldown: Int,
        trigger: TriggerCondition,
        damage: Pair<Pair<Int, Int>, AttackDamage>?,
        animControllerName: String,
        animName: String,
        blockMovementDuration: Int = 0,
    ) : this(
        startPose,
        endPose,
        totalDuration,
        cooldown,
        trigger,
        if (damage != null) listOf(damage) else listOf(),
        animControllerName,
        animName,
        blockMovementDuration,
    )

    open fun canStart(
        attacker: MobEntity,
        target: LivingEntity,
    ) = trigger.doesTrigger(attacker, target)

    open fun start(
        attacker: T,
        target: LivingEntity?,
    ) {
        attacker.triggerAnim(animControllerName, animName)

        if (blockMovementDuration == 0) return
        val blockAbleMovementMob = attacker as? BlockAbleMovementMob<*> ?: return
        blockAbleMovementMob.blockMovement(blockMovementDuration)
    }

    open fun getDamageInstances(
        target: LivingEntity?,
    ): List<AttackDamageInstance> {
        val instances = mutableListOf<AttackDamageInstance>()
        damage.forEach {
            val damage = it.second
            if (damage.requiresTarget() && target == null) return@forEach

            val damageInstance = AttackDamageInstance(it.first, target, damage)
            instances.add(damageInstance)
        }
        return instances
    }
}