package de.fuballer.mcendgame.main.component.entity.custom.attack.fire_geysers

import de.fuballer.mcendgame.main.component.entity.custom.attack.Attack
import de.fuballer.mcendgame.main.component.entity.custom.attack.damage.DelayedAttackDamage
import de.fuballer.mcendgame.main.component.entity.custom.attack.data.AttackAnimationData
import de.fuballer.mcendgame.main.component.entity.custom.attack.trigger_condition.TriggerCondition
import de.maucon.mauconframework.event.EventGateway
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import software.bernie.geckolib.animatable.GeoEntity

class FireGeysersAttack<T>(
    animationData: AttackAnimationData,
    totalDuration: Int,
    cooldown: Int,
    trigger: TriggerCondition,
    damage: List<DelayedAttackDamage>,
    val radius: Int,
    val geyserProbability: Double, // probability for each possible location to have a geyser
    val indicatorDuration: Int,
    val pillarDuration: Int,
    val geyserCountLimit: Int = Int.MAX_VALUE,
    blockMovementDuration: Int = 0,
) : Attack<T>(animationData, totalDuration, cooldown, trigger, damage, blockMovementDuration) where T : MobEntity, T : GeoEntity {
    constructor(
        animationData: AttackAnimationData,
        totalDuration: Int,
        cooldown: Int,
        trigger: TriggerCondition,
        damage: DelayedAttackDamage?,
        radius: Int,
        geyserProbability: Double,
        indicatorDuration: Int,
        pillarDuration: Int,
        geyserCountLimit: Int = Int.MAX_VALUE,
        blockMovementDuration: Int = 0,
    ) : this(
        animationData,
        totalDuration,
        cooldown,
        trigger,
        if (damage != null) listOf(damage) else listOf(),
        radius,
        geyserProbability,
        indicatorDuration,
        pillarDuration,
        geyserCountLimit,
        blockMovementDuration,
    )

    override fun start(attacker: T, target: LivingEntity?) {
        super.start(attacker, target)

        val event = FireGeysersAttackEvent(attacker, target, radius, geyserProbability, geyserCountLimit, indicatorDuration, pillarDuration)
        EventGateway.publish(event)
    }
}