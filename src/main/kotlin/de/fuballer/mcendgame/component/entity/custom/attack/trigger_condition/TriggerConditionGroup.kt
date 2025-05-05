package de.fuballer.mcendgame.component.entity.custom.attack.trigger_condition

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class TriggerConditionGroup(
    private val joinType: TriggerConditionJoinType,
    private val trigger: List<TriggerCondition>,
) : TriggerCondition() {
    override fun doesTrigger(
        attacker: MobEntity,
        target: LivingEntity?,
    ) = when (joinType) {
        TriggerConditionJoinType.OR -> trigger.any { it.doesTrigger(attacker, target) }
        TriggerConditionJoinType.AND -> trigger.all { it.doesTrigger(attacker, target) }
    }

    enum class TriggerConditionJoinType {
        OR,
        AND,
    }
}