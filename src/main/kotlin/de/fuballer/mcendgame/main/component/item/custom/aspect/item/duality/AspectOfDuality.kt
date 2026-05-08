package de.fuballer.mcendgame.main.component.item.custom.aspect.item.duality

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.Text

class AspectOfDuality(
    settings: Settings,
) : AspectItem(settings) {
    override val tier = 2
    override val limit = 1
    override val description = mutableListOf(
        Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "duality_0"),
        Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "duality_1"),
    )
    override val disabledAspects = listOf<AspectItem>()
}