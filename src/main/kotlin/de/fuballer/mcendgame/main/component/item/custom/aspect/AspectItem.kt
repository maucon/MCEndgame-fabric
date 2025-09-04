package de.fuballer.mcendgame.main.component.item.custom.aspect

import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.LoreComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting

abstract class AspectItem(
    settings: Settings,
) : Item(settings) {
    companion object {
        const val TRANSLATABLE_BASE_KEY = "item.mcendgame.aspect."
        const val TRANSLATABLE_DESCRIPTION_KEY = TRANSLATABLE_BASE_KEY + "description."
    }

    abstract val tier: Int
    abstract val limit: Int

    abstract val description: List<MutableText>

    abstract val disabledAspects: List<AspectItem>

    override fun getDefaultStack(): ItemStack {
        val stack = super.getDefaultStack()

        val list = mutableListOf<Text>()
        description.forEach {
            list.add(it.styled { style -> style.withItalic(false).withColor(Formatting.GREEN) })
        }
        list.add(Text.translatable(TRANSLATABLE_BASE_KEY + "limit", limit).styled { style -> style.withItalic(false).withColor(Formatting.GRAY) })

        stack.set(DataComponentTypes.LORE, LoreComponent(list))

        return stack
    }
}