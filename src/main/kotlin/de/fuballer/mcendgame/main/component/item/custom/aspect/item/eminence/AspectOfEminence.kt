package de.fuballer.mcendgame.main.component.item.custom.aspect.item.eminence

import de.fuballer.mcendgame.main.component.custom_attribute.AttributeFormats
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.Text

class AspectOfEminence(
    settings: Settings,
) : AspectItem(settings) {
    companion object {
        const val ADDITIONAL_ATTRIBUTE_PROBABILITY = 0.5
    }

    override val tier = 2
    override val limit = 1
    override val description = mutableListOf(Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "eminence", AttributeFormats.formatDouble(ADDITIONAL_ATTRIBUTE_PROBABILITY * 100)))
    override val disabledAspects = listOf<AspectItem>()
}