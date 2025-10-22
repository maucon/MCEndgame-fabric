package de.fuballer.mcendgame.main.component.item.custom.aspect.item.ancestors

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.Text

class AspectOfAncestors(
    settings: Settings,
) : AspectItem(settings) {
    override val tier = 2
    override val limit = 4
    override val description = mutableListOf(Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "ancestors"))
    override val disabledAspects = listOf<AspectItem>()
}