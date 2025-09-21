package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

data class LivingEntityIgnoreDamageCommand(
    val world: World,
    val entity: LivingEntity,
    val damageSource: DamageSource,
    val amount: Float,
    var ignoresDamage: Boolean = false,
) {
    companion object {
        fun of(entity: LivingEntity, damageSource: DamageSource, amount: Float) = LivingEntityIgnoreDamageCommand(entity.world, entity, damageSource, amount)
    }
}