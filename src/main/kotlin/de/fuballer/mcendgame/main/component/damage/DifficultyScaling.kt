package de.fuballer.mcendgame.main.component.damage

import kotlin.math.min

enum class DifficultyScaling(
    val scaleDamage: (Float) -> Float,
) {
    NONE({ it }),
    EASY({ min(it / 2 + 1, it) }),
    HARD({ it * 3 / 2 }),
}