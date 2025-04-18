package de.fuballer.mcendgame.components.dungeon.enemy.equipment.attributes

import de.fuballer.mcendgame.util.random.SortableRandomOption

object AttributeSettings {
    val ATTRIBUTE_COUNT = listOf(
        SortableRandomOption(5000, 0, 0),
        SortableRandomOption(3000, 1, 1),
        SortableRandomOption(800, 2, 2),
        SortableRandomOption(150, 3, 3),
        SortableRandomOption(25, 4, 4),
        SortableRandomOption(1, 5, 5),
    )
}