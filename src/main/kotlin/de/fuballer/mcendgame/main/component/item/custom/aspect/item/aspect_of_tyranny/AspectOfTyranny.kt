package de.fuballer.mcendgame.main.component.item.custom.aspect.item.aspect_of_tyranny

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.MutableText
import net.minecraft.text.Text

class AspectOfTyranny(
    settings: Settings,
) : AspectItem(settings) {
    companion object {
        const val ADDITIONAL_ELITES = 2
    }

    override val tier = 2
    override val limit = 4
    override val description: MutableText = Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "tyranny", ADDITIONAL_ELITES)
    override val disabledAspects = listOf<AspectItem>()
}