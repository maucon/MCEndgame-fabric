package de.fuballer.mcendgame.main.component.item.custom.crystal

import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeSettings
import de.fuballer.mcendgame.main.component.corruption.CorruptionExtensions.isCorrupted
import de.fuballer.mcendgame.main.component.dungeon.loot.drop.ItemColor
import de.fuballer.mcendgame.main.util.extension.ItemStackExtension.isForgeable
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.LoreComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting

abstract class CrystalItem(
    settings: Settings,
) : Item(settings) {
    companion object {
        const val DESCRIPTION_BASE_KEY = "item.mcendgame.crystal.description."
    }

    abstract val description: MutableText

    override fun getDefaultStack(): ItemStack {
        val stack = super.getDefaultStack()

        val list = mutableListOf<Text>()
        list.add(description.styled { style -> style.withItalic(false).withColor(Formatting.GRAY) })
        stack.set(DataComponentTypes.LORE, LoreComponent(list))

        return stack
    }

    override fun getName(stack: ItemStack): MutableText = super.getName(stack).copy().withColor(ItemColor.CRYSTAL.intColor)

    open fun canForge(stack: ItemStack): MutableText? {
        if (stack.isEmpty) return CrystalForgeSettings.getForgeErrorText("no_item")
        if (!stack.isForgeable()) return CrystalForgeSettings.getForgeErrorText("item_not_forgeable")
        if (stack.isCorrupted()) return CrystalForgeSettings.getForgeErrorText("item_corrupted")
        return null
    }

    abstract fun forge(stack: ItemStack): ItemStack
}