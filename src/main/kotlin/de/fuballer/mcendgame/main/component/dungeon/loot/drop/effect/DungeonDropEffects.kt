package de.fuballer.mcendgame.main.component.dungeon.loot.drop.effect

import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundEvents

object DungeonDropEffects {
    val UNIQUE = DungeonDropEffect(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, ParticleTypes.DRAGON_BREATH, 16732160)
        .withVolume(2F)
        .withParticleCount(20)
        .withParticleSpeed(0.03)
    val UNIQUE_PLAYER_DROPPED = DungeonDropEffect(glowColor = 16732160)

    val ASPECT = DungeonDropEffect(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, ParticleTypes.SOUL_FIRE_FLAME, 6616575)
        .withVolume(2F)
        .withParticleCount(20)
        .withParticleSpeed(0.03)
    val ASPECT_PLAYER_DROPPED = DungeonDropEffect(glowColor = 6616575)
}