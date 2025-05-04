package de.fuballer.mcendgame.component.entity.custom.attack.trigger_condition

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class AlwaysTrueTriggerCondition : TriggerCondition() {
    override fun doesTrigger(attacker: MobEntity, target: LivingEntity?) = true
}