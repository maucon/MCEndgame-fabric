package de.fuballer.mcendgame.main.component.item.custom.aspect.item.ghosts

import de.fuballer.mcendgame.main.component.item.custom.armor.CustomArmorItems
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import net.minecraft.text.Text

class AspectOfGhosts(
    settings: Settings,
) : AspectItem(settings) {
    companion object {
        const val FORCED_DUNGEON_LEVEL = 10

        const val RANDOM_GHOST_MIN_DUNGEON_LEVEL = 8
        const val RANDOM_GHOST_PROBABILITY = 0.0001
    }

    override val tier = 1
    override val limit = 1
    override val description = mutableListOf(
        Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "ghosts_0", FORCED_DUNGEON_LEVEL),
        Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "ghosts_1"),
        Text.translatable(TRANSLATABLE_DESCRIPTION_KEY + "ghosts_2", CustomArmorItems.GEISTERGALOSCHEN.name)
    )
    override val disabledAspects = listOf(AspectItems.ASPECT_OF_IMPATIENCE)
}