package de.fuballer.mcendgame.components.corruption

import de.fuballer.mcendgame.components.corruption.CorruptionExtensions.isCorrupted
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting

private val CORRUPTION_TEXT = Text.translatable("mcendgame.corrupted").formatted(Formatting.DARK_RED)

@Injectable
@Environment(EnvType.CLIENT)
class CorruptionRenderer {
    @Initialize
    fun on() = ItemTooltipCallback.EVENT.register { itemStack: ItemStack, _: Item.TooltipContext, _: TooltipType, texts: MutableList<Text> ->
        if (itemStack.isCorrupted()) {
            texts.add(1, Text.empty())
            texts.add(1, CORRUPTION_TEXT)
        }
    }
}