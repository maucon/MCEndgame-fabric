package de.fuballer.mcendgame.main.util.extension

import java.awt.Color

object ColorExtension {
    fun Color.toInt() = (alpha shl 24) or (red shl 16) or (green shl 8) or blue
}