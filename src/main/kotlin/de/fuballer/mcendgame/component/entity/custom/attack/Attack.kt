package de.fuballer.mcendgame.component.entity.custom.attack

import de.fuballer.mcendgame.component.entity.custom.attack.damage.DelayedAttackDamage
import de.fuballer.mcendgame.component.entity.custom.attack.damage.instance.AttackDamageInstance
import de.fuballer.mcendgame.component.entity.custom.attack.data.AttackAnimationData
import de.fuballer.mcendgame.component.entity.custom.attack.trigger_condition.TriggerCondition
import de.fuballer.mcendgame.component.entity.custom.interfaces.BlockAbleMovementMob
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import software.bernie.geckolib.animatable.GeoEntity

open class Attack<T>(
    val animationData: AttackAnimationData,
    val totalDuration: Int,
    val cooldown: Int,
    private val trigger: TriggerCondition,
    private val damage: List<DelayedAttackDamage>,
    private val blockMovementDuration: Int = 0,
) where T : MobEntity, T : GeoEntity {
    constructor(
        animationData: AttackAnimationData,
        totalDuration: Int,
        cooldown: Int,
        trigger: TriggerCondition,
        damage: DelayedAttackDamage?,
        blockMovementDuration: Int = 0,
    ) : this(
        animationData,
        totalDuration,
        cooldown,
        trigger,
        if (damage != null) listOf(damage) else listOf(),
        blockMovementDuration,
    )

    open fun canStart(
        attacker: MobEntity,
        target: LivingEntity?,
    ) = trigger.doesTrigger(attacker, target)

    open fun start(
        attacker: T,
        target: LivingEntity?,
    ) {
        animationData.triggerAnimation(attacker)

        if (blockMovementDuration == 0) return
        val blockAbleMovementMob = attacker as? BlockAbleMovementMob<*> ?: return
        blockAbleMovementMob.blockMovement(blockMovementDuration)
    }

    open fun getDamageInstances(
        target: LivingEntity?,
    ): List<AttackDamageInstance> {
        val instances = mutableListOf<AttackDamageInstance>()
        damage.forEach {
            if (it.damage.requiresTarget() && target == null) return@forEach

            val damageInstance = getDamageInstance(target, it)
            instances.add(damageInstance)
        }
        return instances
    }

    open fun getDamageInstance(
        target: LivingEntity?,
        delayedDamage: DelayedAttackDamage,
    ) = AttackDamageInstance(delayedDamage.minDelay, delayedDamage.maxDelay, target, delayedDamage.damage)
}