package de.fuballer.mcendgame.component.entity.custom.attack

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class CustomLeapAttackDamageInstance(
    timeRange: Pair<Int, Int>,
    target: LivingEntity?,
    damage: CustomAttackDamage,
) : CustomAttackDamageInstance(timeRange, target, damage) {
    override fun shouldCancel(damager: MobEntity) = damager.isOnGround
}