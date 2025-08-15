package de.fuballer.mcendgame.main.component.item.custom.crystal.item.corruption

import de.fuballer.mcendgame.main.component.corruption.CorruptionExtensions.setCorrupted
import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import de.fuballer.mcendgame.main.util.random.RandomUtil
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text

class CorruptionCrystalItem(
    settings: Settings,
) : CrystalItem(settings) {
    override val description: MutableText = Text.translatable(DESCRIPTION_BASE_KEY + "corruption")

    override fun canForge(stack: ItemStack): MutableText? {
        return super.canForge(stack)
    }

    override fun forge(stack: ItemStack): ItemStack {
        val newStack = stack.copy()

        val possibleOutcomes = CorruptionSettings.CORRUPTION_OUTCOMES.toList().filter { it.option.canApply(newStack) }
        val outcome = RandomUtil.pick(possibleOutcomes).option

        val result = outcome.apply(stack)
        result.setCorrupted()

        return result
    }
}