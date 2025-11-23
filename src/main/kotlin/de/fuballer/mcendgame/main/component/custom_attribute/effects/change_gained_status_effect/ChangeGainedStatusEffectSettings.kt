package de.fuballer.mcendgame.main.component.custom_attribute.effects.change_gained_status_effect

import kotlin.math.min

object ChangeGainedStatusEffectSettings {
    val EFFECT_PAIRS = listOf(
        Pair(GainedStatusEffect.POISON, GainedStatusEffect.REGENERATION),
        Pair(GainedStatusEffect.WEAKNESS, GainedStatusEffect.STRENGTH),
        Pair(GainedStatusEffect.SLOWNESS, GainedStatusEffect.SPEED),
        Pair(GainedStatusEffect.WITHER, GainedStatusEffect.RESISTANCE),
    )

    fun getStatusEffectPairs(count: Int) = EFFECT_PAIRS.shuffled().take(min(count, EFFECT_PAIRS.size))
}