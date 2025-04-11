package de.fuballer.mcendgame.components.corruption

import de.fuballer.mcendgame.components.corruption.CorruptionExtensions.isCorrupted
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable
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
    @Initializer
    fun on() = ItemTooltipCallback.EVENT.register { itemStack: ItemStack, _: Item.TooltipContext, tooltipType: TooltipType, texts: MutableList<Text> ->
        if (!itemStack.isCorrupted()) return@register

        if (tooltipType.isAdvanced) {
            texts.add(texts.size - 2, CORRUPTION_TEXT)
        } else {
            texts.add(CORRUPTION_TEXT)
        }
    }
}