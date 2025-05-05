package de.fuballer.mcendgame.client.messaging

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text

data class RenderItemTooltipCommand(
    val itemStack: ItemStack,
    val context: Item.TooltipContext,
    val tooltipType: TooltipType,
    val texts: MutableList<Text>
)