package de.fuballer.mcendgame.main.component.item.custom.totem

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.setCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.dungeon.loot.drop.ItemColor
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.LoreComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import kotlin.math.min

abstract class TotemItem(
    settings: Settings,
) : Item(settings) {
    companion object {
        const val TIER_KEY = "item.mcendgame.totem.tier"
    }

    abstract val maxTier: Int

    abstract fun getCustomAttributes(tier: Int): List<CustomAttribute>

    fun getStack(tier: Int = 0): ItemStack {
        val stack = super.getDefaultStack()

        val limitedRarity = min(tier, maxTier)
        addRarityLore(stack, tier)
        stack.setCustomAttributes(getCustomAttributes(limitedRarity), AttributeModifierSlot.CHEST) //TODO add custom slot

        return stack
    }

    private fun addRarityLore(stack: ItemStack, tier: Int) {
        val lore = listOf(Text.translatable(TIER_KEY, tier).styled { style -> style.withItalic(false).withColor(Formatting.GRAY) })
        stack.set(DataComponentTypes.LORE, LoreComponent(lore))
    }

    override fun getName(stack: ItemStack): MutableText = super.getName(stack).copy().withColor(ItemColor.TOTEM.color)

    override fun getDefaultStack() = getStack()
}