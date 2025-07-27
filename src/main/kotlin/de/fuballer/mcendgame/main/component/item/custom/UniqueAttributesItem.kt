package de.fuballer.mcendgame.main.component.item.custom

import net.minecraft.item.Item

abstract class UniqueAttributesItem(
    val settings: Settings,
) : Item(settings), UniqueAttributesItemInterface {
    override fun getDefaultStack() = getRolledStack(this, true)
}