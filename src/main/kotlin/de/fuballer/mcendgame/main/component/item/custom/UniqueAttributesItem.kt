package de.fuballer.mcendgame.main.component.item.custom

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText

abstract class UniqueAttributesItem(
    val settings: Settings,
) : Item(settings), UniqueAttributesItemInterface {
    override fun getDefaultStack() = getRolledStack(this, true)

    override fun getName(stack: ItemStack): MutableText = super.getName(stack).copy().withColor(getNameColor())
}