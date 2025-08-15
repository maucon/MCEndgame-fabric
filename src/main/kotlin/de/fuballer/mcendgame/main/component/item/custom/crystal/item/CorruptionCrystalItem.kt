package de.fuballer.mcendgame.main.component.item.custom.crystal.item

import de.fuballer.mcendgame.main.component.corruption.CorruptionService
import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text

class CorruptionCrystalItem(
    settings: Settings,
) : CrystalItem(settings) {
    override val description: MutableText = Text.translatable(DESCRIPTION_BASE_KEY + "corruption")

    override fun forge(stack: ItemStack) = CorruptionService.corrupt(stack)
}