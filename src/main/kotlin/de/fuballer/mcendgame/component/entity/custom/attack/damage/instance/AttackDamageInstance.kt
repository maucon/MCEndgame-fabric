package de.fuballer.mcendgame.component.entity.custom.attack.damage.instance

import de.fuballer.mcendgame.component.entity.custom.attack.damage.AttackDamage
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld

open class AttackDamageInstance(
    private var timeRange: Pair<Int, Int>,
    private val target: LivingEntity?,
    private val damage: AttackDamage,
) {
    var age = 0

    // returns if the damage is applied, expired or cancelled
    fun tick(
        world: ServerWorld,
        damager: MobEntity,
    ): Boolean {
        if (shouldCancel(damager)) return true

        age++
        if (age < timeRange.first) return false
        if (age > timeRange.second) return true

        return damage.apply(world, damager, target)
    }

    open fun shouldCancel(damager: MobEntity) = false
}