package de.fuballer.mcendgame.main.component.item.custom.aspect.item.impatience

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.Text

class AspectOfImpatience(
    settings: Settings,
) : AspectItem(settings) {
    companion object {
        const val ADDITIONAL_LEVELS = 2
    }

    override val tier = 2
    override val limit = 4
    override val description = mutableListOf(Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "impatience", ADDITIONAL_LEVELS))
    override val disabledAspects = listOf<AspectItem>()
}