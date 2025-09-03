package de.fuballer.mcendgame.main.util

object ColorUtil {
    fun rgbaToInt(r: Int, g: Int, b: Int, a: Int) =
        (a and 0xFF shl 24) or
                (r and 0xFF shl 16) or
                (g and 0xFF shl 8) or
                (b and 0xFF)
}