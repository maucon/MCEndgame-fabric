package de.fuballer.mcendgame.components.entity.custom.attack

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.server.world.ServerWorld

class CustomAttackDamageInstance(
    private var delay: Int,
    private val target: LivingEntity,
    private val attack: CustomAttack,
) {
    fun tick() = --delay == 0

    fun apply(world: ServerWorld, damager: MobEntity) = attack.apply(world, damager, target)
}