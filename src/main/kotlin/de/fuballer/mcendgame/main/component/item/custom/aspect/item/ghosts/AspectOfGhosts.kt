package de.fuballer.mcendgame.main.component.item.custom.aspect.item.ghosts

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.text.MutableText
import net.minecraft.text.Text

class AspectOfGhosts(
    settings: Settings,
) : AspectItem(settings) {
    override val tier = 2
    override val limit = 1
    override val description: MutableText = Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "ghosts")
    override val disabledAspects = listOf<AspectItem>()
}