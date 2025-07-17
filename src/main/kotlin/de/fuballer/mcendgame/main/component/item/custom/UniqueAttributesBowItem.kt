package de.fuballer.mcendgame.main.component.item.custom

import net.minecraft.item.BowItem

abstract class UniqueAttributesBowItem(
    val settings: Settings,
) : BowItem(settings), UniqueAttributesItemInterface {
    override fun getDefaultStack() = getRolledStack(this, true)
}