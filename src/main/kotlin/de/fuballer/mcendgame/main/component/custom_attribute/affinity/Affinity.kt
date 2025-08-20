package de.fuballer.mcendgame.main.component.custom_attribute.affinity

enum class Affinity {
    BENEFICIAL,
    DETRIMENTAL,
    NEUTRAL;

    fun invert() = when (this) {
        BENEFICIAL -> DETRIMENTAL
        DETRIMENTAL -> BENEFICIAL
        NEUTRAL -> NEUTRAL
    }
}