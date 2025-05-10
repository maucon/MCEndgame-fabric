package de.fuballer.mcendgame.main.util

object ColorUtil {
    fun getIntColor(r: Int, g: Int, b: Int, a: Int = 255) = (a shl 24) or (r shl 16) or (g shl 8) or b
}