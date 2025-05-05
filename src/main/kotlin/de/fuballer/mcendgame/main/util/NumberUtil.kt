package de.fuballer.mcendgame.main.util

private val ROMAN_NUMERALS = mapOf(
    1000 to "M",
    900 to "CM",
    500 to "D",
    400 to "CD",
    100 to "C",
    90 to "XC",
    50 to "L",
    40 to "XL",
    10 to "X",
    9 to "IX",
    5 to "V",
    4 to "IV",
    1 to "I"
)

object NumberUtil {
    fun toRomanNumeral(number: Int): String {
        val stringBuilder = StringBuilder()
        var remaining = number

        for ((value, numeral) in ROMAN_NUMERALS.entries.sortedByDescending { it.key }) {
            while (remaining >= value) {
                stringBuilder.append(numeral)
                remaining -= value
            }
        }

        return stringBuilder.toString()
    }
}