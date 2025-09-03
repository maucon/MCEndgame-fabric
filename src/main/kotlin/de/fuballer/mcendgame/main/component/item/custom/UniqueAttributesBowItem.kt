package de.fuballer.mcendgame.main.component.item.custom

import net.minecraft.item.BowItem
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText

abstract class UniqueAttributesBowItem(
    val settings: Settings,
) : BowItem(settings), UniqueAttributesItemInterface {
    override fun getDefaultStack() = getRolledStack(this, true)

    override fun getName(stack: ItemStack): MutableText = super.getName(stack).copy().withColor(getNameColor())
}