package de.fuballer.mcendgame.main.component.damage.ignore_damage

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

data class IgnoreDamageCommand(
    val world: World,
    val entity: LivingEntity,
    val damageSource: DamageSource,
    var ignoreDamage: Boolean = false,
) {
    companion object {
        fun of(entity: LivingEntity, damageSource: DamageSource) =
            IgnoreDamageCommand(entity.entityWorld, entity, damageSource)
    }
}