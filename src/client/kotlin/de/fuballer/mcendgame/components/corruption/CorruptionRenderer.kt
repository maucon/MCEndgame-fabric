package de.fuballer.mcendgame.components.corruption

import de.fuballer.mcendgame.functional.item_tag.ItemTag
import de.fuballer.mcendgame.functional.item_tag.ItemTagsExtensions.addItemTag
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text

@Injectable
class CorruptionRenderer {
    @Initialize
    fun on() = ItemTooltipCallback.EVENT.register { itemStack: ItemStack, _: Item.TooltipContext, _: TooltipType, texts: MutableList<Text> ->
        texts.add(Text.literal("Cheeseburger"))
        itemStack.addItemTag(ItemTag.CORRUPTED)
    }
}