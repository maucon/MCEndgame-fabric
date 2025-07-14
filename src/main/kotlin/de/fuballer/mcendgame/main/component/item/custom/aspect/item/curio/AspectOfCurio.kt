package de.fuballer.mcendgame.main.component.item.custom.aspect.item.curio

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.MutableText
import net.minecraft.text.Text

class AspectOfCurio(
    settings: Settings,
) : AspectItem(settings) {
    companion object {
        const val INCREASED_UNIQUES = 1.0
    }

    override val tier = 2
    override val limit = 4
    override val description: MutableText = Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "curio", (INCREASED_UNIQUES * 100).toInt())
    override val disabledAspects = listOf<AspectItem>()
}