package de.fuballer.mcendgame.main.component.dungeon.loot.drop

import de.fuballer.mcendgame.main.util.extension.ColorExtension.toInt
import java.awt.Color

enum class ItemColor(
    val intColor: Int,
) {
    UNIQUE(Color(255, 80, 0).toInt()),
    ASPECT(Color(100, 245, 255).toInt()),
    CRYSTAL(Color(208, 0, 255).toInt()),
    TOTEM_BASIC(Color(30, 240, 20).toInt()),
    TOTEM_EFFECT(Color(240, 240, 20).toInt()),
    TOTEM_ABILITY(Color(240, 30, 20).toInt()),
}