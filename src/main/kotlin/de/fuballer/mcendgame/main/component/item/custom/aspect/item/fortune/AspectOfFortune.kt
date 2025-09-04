package de.fuballer.mcendgame.main.component.item.custom.aspect.item.fortune

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.Text

class AspectOfFortune(
    settings: Settings,
) : AspectItem(settings) {
    override val tier = 2
    override val limit = 1
    override val description = mutableListOf(Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "fortune"))
    override val disabledAspects = listOf<AspectItem>()
}