package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.ItemEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

data class ItemDropEvent(
    val entity: ItemEntity,
    val stack: ItemStack,
    val world: World,
) {
    companion object {
        fun of(entity: ItemEntity) = ItemDropEvent(entity, entity.stack, entity.world)
    }
}