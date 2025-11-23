package de.fuballer.mcendgame.main.component.custom_attribute.effects.change_gained_status_effect

import de.fuballer.mcendgame.main.component.custom_attribute.effects.change_gained_status_effect.GainedStatusEffect.entries
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.registry.entry.RegistryEntry

enum class GainedStatusEffect(
    val displayName: String,
    val effect: RegistryEntry<StatusEffect>,
) {
    REGENERATION("Regeneration", StatusEffects.REGENERATION),
    POISON("Poison", StatusEffects.POISON),
    STRENGTH("Strength", StatusEffects.STRENGTH),
    WEAKNESS("Weakness", StatusEffects.WEAKNESS),
    SPEED("Speed", StatusEffects.SPEED),
    SLOWNESS("Slowness", StatusEffects.SLOWNESS),
    WITHER("Wither", StatusEffects.WITHER),
    RESISTANCE("Resistance", StatusEffects.RESISTANCE);

    companion object {
        fun fromString(name: String) = entries.firstOrNull { it.displayName == name }
    }
}