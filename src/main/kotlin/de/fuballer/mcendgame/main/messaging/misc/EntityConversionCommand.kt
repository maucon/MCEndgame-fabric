package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.LivingEntity
import net.minecraft.world.World

data class EntityConversionCommand(
    val world: World,
    val entity: LivingEntity,
    var canConvert: Boolean = true,
) {
    companion object {
        fun of(entity: LivingEntity) = EntityConversionCommand(entity.world, entity)
    }
}