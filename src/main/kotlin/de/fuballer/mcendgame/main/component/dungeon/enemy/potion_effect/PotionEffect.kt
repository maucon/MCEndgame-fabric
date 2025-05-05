package de.fuballer.mcendgame.main.component.dungeon.enemy.potion_effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.registry.entry.RegistryEntry

enum class PotionEffect(
    private val type: RegistryEntry<StatusEffect>,
    private val amplifier: Int = 0,
    private val duration: Int = Int.MAX_VALUE
) {
    STRENGTH_1(StatusEffects.STRENGTH, 0),
    STRENGTH_2(StatusEffects.STRENGTH, 1),
    STRENGTH_3(StatusEffects.STRENGTH, 2),
    STRENGTH_4(StatusEffects.STRENGTH, 3),
    RESISTANCE_1(StatusEffects.RESISTANCE, 0),
    RESISTANCE_2(StatusEffects.RESISTANCE, 1),
    RESISTANCE_3(StatusEffects.RESISTANCE, 2),
    RESISTANCE_4(StatusEffects.RESISTANCE, 3),
    SPEED_1(StatusEffects.SPEED, 0),
    SPEED_2(StatusEffects.SPEED, 1),
    SPEED_3(StatusEffects.SPEED, 2),
    SPEED_4(StatusEffects.SPEED, 3),
    FIRE_RESISTANCE(StatusEffects.FIRE_RESISTANCE),
    WIND_CHARGED(StatusEffects.WIND_CHARGED),
    WEAVING(StatusEffects.WEAVING),
    INVISIBILITY(StatusEffects.INVISIBILITY),
    ;

    fun getEffectInstance(particles: Boolean = true) =
        StatusEffectInstance(type, duration, amplifier, false, particles)
}