package de.fuballer.mcendgame.main.component.item.custom.crystal.item

import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeSettings
import de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.EquipmentGenerationSettings
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItemInterface
import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text

class ReforgeCrystalItem(
    settings: Settings,
) : CrystalItem(settings) {
    override val description: MutableText = Text.translatable(DESCRIPTION_BASE_KEY + "reforge")

    override fun canForge(stack: ItemStack): MutableText? {
        val cannotForgeReason = super.canForge(stack)
        if (cannotForgeReason != null) return cannotForgeReason

        if (stack.item !is UniqueAttributesItemInterface) return CrystalForgeSettings.getForgeErrorText("can_only_forge_unique")

        return null
    }

    override fun forge(stack: ItemStack): ItemStack {
        var uniqueItem: Item
        do {
            uniqueItem = EquipmentGenerationSettings.getRandomUniqueEquipment(tagsExactMatch = false)!!.item
        } while (uniqueItem == stack.item)

        val uniqueInterface = uniqueItem as UniqueAttributesItemInterface
        return uniqueInterface.getRolledStack(uniqueItem)
    }
}