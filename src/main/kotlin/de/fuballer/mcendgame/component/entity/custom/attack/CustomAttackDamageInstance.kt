package de.fuballer.mcendgame.component.entity.custom.attack

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld

class CustomAttackDamageInstance(
    private var delay: Int,
    private val target: LivingEntity?,
    private val damage: CustomAttackDamage,
) {
    fun tick() = --delay == 0

    fun apply(world: ServerWorld, damager: MobEntity) = damage.apply(world, damager, target)
}