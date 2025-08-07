package de.fuballer.mcendgame.main.component.dungeon.loot.drop.selector

import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItemInterface
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

object DungeonDropSelectors {
    val PLAYER_DROPPED: (ItemStack, ItemEntity) -> Boolean = { _, itemEntity -> itemEntity.owner is PlayerEntity }

    val UNIQUE: (ItemStack, ItemEntity) -> Boolean = { itemStack, _ -> itemStack.item is UniqueAttributesItemInterface }
    val UNIQUE_PLAYER_DROPPED: (ItemStack, ItemEntity) -> Boolean = { itemStack, itemEntity -> UNIQUE(itemStack, itemEntity) && PLAYER_DROPPED(itemStack, itemEntity) }

    val ASPECT: (ItemStack, ItemEntity) -> Boolean = { itemStack, _ -> itemStack.item is AspectItem }
    val ASPECT_PLAYER_DROPPED: (ItemStack, ItemEntity) -> Boolean = { itemStack, itemEntity -> ASPECT(itemStack, itemEntity) && PLAYER_DROPPED(itemStack, itemEntity) }
}