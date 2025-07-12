package de.fuballer.mcendgame.main.component.item.custom.aspect.item.dominion

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.MutableText
import net.minecraft.text.Text

class AspectOfDominion(
    settings: Settings,
) : AspectItem(settings) {
    override val tier = 2
    override val limit = 2
    override val description: MutableText = Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "dominion")
    override val disabledAspects = listOf<AspectItem>()
}