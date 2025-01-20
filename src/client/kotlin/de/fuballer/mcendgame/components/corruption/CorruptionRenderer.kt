package de.fuballer.mcendgame.components.corruption

import de.fuballer.mcendgame.components.item_tag.ItemTag
import de.fuballer.mcendgame.components.item_tag.ItemTagsExtensions.addItemTag
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text

@Injectable
class CorruptionRenderer {
    @Initialize
    fun init() {
        ItemTooltipCallback.EVENT.register(::onItemTooltipCallback)
    }

    fun onItemTooltipCallback(stack: ItemStack, tooltipContext: Item.TooltipContext, tooltipType: TooltipType, lines: MutableList<Text>) {
        lines.add(Text.literal("Cheeseburger"))
        stack.addItemTag(ItemTag.CORRUPTED)
    }
}