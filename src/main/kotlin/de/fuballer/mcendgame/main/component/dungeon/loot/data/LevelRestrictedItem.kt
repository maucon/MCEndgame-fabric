package de.fuballer.mcendgame.main.component.dungeon.loot.data

import net.minecraft.item.Item

class LevelRestrictedItem(
    val item: Item,
    val level: Int = 0,
)