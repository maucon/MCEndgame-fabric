package de.fuballer.mcendgame.main.component.item.custom.aspect

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class AspectItem(
    settings: Settings,
) : Item(settings) {
    override fun hasGlint(stack: ItemStack) = true
}