package de.fuballer.mcendgame.main.component.entity.custom.attack.damage.instance

import de.fuballer.mcendgame.main.component.entity.custom.attack.damage.AttackDamage
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity

class LeapAttackDamageInstance(
    minDelay: Int,
    maxDelay: Int,
    target: LivingEntity?,
    damage: AttackDamage,
) : AttackDamageInstance(minDelay, maxDelay, target, damage) {
    override fun shouldCancel(damager: MobEntity) = damager.isOnGround
}