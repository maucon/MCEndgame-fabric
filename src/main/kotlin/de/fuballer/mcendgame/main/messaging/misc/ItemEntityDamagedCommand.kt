package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.ItemEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.world.World

data class ItemEntityDamagedCommand(
    val world: World,
    val entity: ItemEntity,
    val source: DamageSource,
    var ignoresDamage: Boolean = false,
) {
    companion object {
        fun of(entity: ItemEntity, source: DamageSource) = ItemEntityDamagedCommand(entity.world, entity, source)
    }
}