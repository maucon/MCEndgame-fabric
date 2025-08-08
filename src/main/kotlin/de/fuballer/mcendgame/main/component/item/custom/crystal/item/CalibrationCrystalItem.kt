package de.fuballer.mcendgame.main.component.item.custom.crystal.item

import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeSettings
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.setCustomAttributes
import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text

class CalibrationCrystalItem(
    settings: Settings,
) : CrystalItem(settings) {
    override val description: MutableText = Text.translatable(DESCRIPTION_BASE_KEY + "calibration")

    override fun canForge(stack: ItemStack): MutableText? {
        val cannotForgeReason = super.canForge(stack)
        if (cannotForgeReason != null) return cannotForgeReason

        val attributes = stack.getCustomAttributes().filter { it.hasNonZeroRange() }
        if (attributes.isEmpty()) return CrystalForgeSettings.getForgeErrorText("no_attribute_with_range")

        return null
    }

    override fun forge(stack: ItemStack): ItemStack {
        val newStack = stack.copy()

        val oldAttributes = stack.getCustomAttributes()
        if (oldAttributes.isEmpty()) return newStack
        val slot = oldAttributes[0].slot

        val newAttributes = oldAttributes.map { it.getRerolled() }
        newStack.setCustomAttributes(newAttributes, slot)

        return newStack
    }
}