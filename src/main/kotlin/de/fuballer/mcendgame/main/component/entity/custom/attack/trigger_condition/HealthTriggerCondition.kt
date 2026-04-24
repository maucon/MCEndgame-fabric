package de.fuballer.mcendgame.main.component.entity.custom.attack.trigger_condition

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class HealthTriggerCondition(
    private val minHealthPercent: Double,
    private val maxHealthPercent: Double,
) : TriggerCondition() {
    override fun doesTrigger(attacker: MobEntity, target: LivingEntity?): Boolean {
        val percent = attacker.health / attacker.maxHealth
        return percent in minHealthPercent..maxHealthPercent
    }
}