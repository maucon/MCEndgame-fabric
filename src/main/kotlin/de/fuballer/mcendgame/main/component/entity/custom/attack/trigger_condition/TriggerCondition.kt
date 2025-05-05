package de.fuballer.mcendgame.main.component.entity.custom.attack.trigger_condition

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

abstract class TriggerCondition {
    abstract fun doesTrigger(attacker: MobEntity, target: LivingEntity?): Boolean
}