package de.fuballer.mcendgame.main.component.item.custom

import net.minecraft.item.GoatHornItem
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText

abstract class UniqueAttributesHornItem(
    val settings: Settings,
) : GoatHornItem(settings), UniqueAttributesItemInterface {
    override fun getDefaultStack() = getRolledStack(this, true)

    override fun getName(stack: ItemStack): MutableText = super.getName(stack).copy().withColor(getNameColor())
}