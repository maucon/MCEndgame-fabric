package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

data class LivingEntityDamageCommand(
    val world: World,
    val entity: LivingEntity,
    val damageSource: DamageSource,
    val amount: Float,
    var dealsDamage: Boolean = true,
) {
    companion object {
        fun of(entity: LivingEntity, damageSource: DamageSource, amount: Float) = LivingEntityDamageCommand(entity.world, entity, damageSource, amount)
    }
}