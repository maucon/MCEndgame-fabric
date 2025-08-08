package de.fuballer.mcendgame.main.component.item.custom.crystal

import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.LoreComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting

abstract class CrystalItem(
    settings: Settings,
) : Item(settings) {
    companion object {
        const val TRANSLATABLE_BASE_KEY = "item.mcendgame.orb."
    }

    override fun getDefaultStack(): ItemStack {
        val stack = super.getDefaultStack()

        val list = mutableListOf<Text>()
        list.add(Text.translatable(TRANSLATABLE_BASE_KEY).styled { style -> style.withItalic(false).withColor(Formatting.GRAY) })
        stack.set(DataComponentTypes.LORE, LoreComponent(list))

        return stack
    }

    abstract fun canForge(stack: ItemStack): Boolean

    abstract fun forge(stack: ItemStack): ItemStack
}