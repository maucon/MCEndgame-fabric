package de.fuballer.mcendgame.main.component.item.custom.aspect.item.zeal

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.MutableText
import net.minecraft.text.Text

class AspectOfZeal(
    settings: Settings,
) : AspectItem(settings) {
    companion object {
        const val ADDITIONAL_PROGRESS = 2
    }

    override val tier = 2
    override val limit = 4
    override val description: MutableText = Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "zeal", ADDITIONAL_PROGRESS)
    override val disabledAspects = listOf<AspectItem>()
}