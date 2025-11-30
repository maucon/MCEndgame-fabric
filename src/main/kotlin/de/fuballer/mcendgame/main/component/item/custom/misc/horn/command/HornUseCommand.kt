package de.fuballer.mcendgame.main.component.item.custom.misc.horn.command

import net.minecraft.entity.player.PlayerEntity

data class HornUseCommand(
    val user: PlayerEntity,
    val moreDuration: MutableList<Double> = mutableListOf(),
    val moreCooldown: MutableList<Double> = mutableListOf(),
    var isStronger: Boolean = false,
) {
    fun getDurationFactor() = moreDuration.fold(1.0) { a, b -> a * (b + 1) }
    fun getCooldownFactor() = moreCooldown.fold(1.0) { a, b -> a * (b + 1) }
}