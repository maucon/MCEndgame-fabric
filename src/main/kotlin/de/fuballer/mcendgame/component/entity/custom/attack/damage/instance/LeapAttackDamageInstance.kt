package de.fuballer.mcendgame.component.entity.custom.attack.damage.instance

import de.fuballer.mcendgame.component.entity.custom.attack.damage.AttackDamage
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class LeapAttackDamageInstance(
    timeRange: Pair<Int, Int>,
    target: LivingEntity?,
    damage: AttackDamage,
) : AttackDamageInstance(timeRange, target, damage) {
    override fun shouldCancel(damager: MobEntity) = damager.isOnGround
}