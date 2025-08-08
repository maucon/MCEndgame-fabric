package de.fuballer.mcendgame.main.component.item.custom.crystal.item

import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import net.minecraft.item.ItemStack

class CorruptionCrystalItem(
    settings: Settings
) : CrystalItem(settings) {
    override fun canForge(stack: ItemStack): Boolean {
        return true
    }

    override fun forge(stack: ItemStack): ItemStack {
        return stack.copy()
    }
}