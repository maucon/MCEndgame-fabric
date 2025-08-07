package de.fuballer.mcendgame.main.component.dungeon.loot.drop.effect

import de.fuballer.mcendgame.main.component.dungeon.loot.drop.ItemColor
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundEvents

object DungeonDropEffects {
    val UNIQUE =
        DungeonDropEffect(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, ParticleTypes.DRAGON_BREATH, ItemColor.UNIQUE)
            .withVolume(2F)
            .withParticleCount(20)
            .withParticleSpeed(0.03)
    val UNIQUE_PLAYER_DROPPED = DungeonDropEffect(glowColor = ItemColor.UNIQUE)

    val ASPECT =
        DungeonDropEffect(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, ParticleTypes.SOUL_FIRE_FLAME, ItemColor.ASPECT)
            .withVolume(2F)
            .withParticleCount(20)
            .withParticleSpeed(0.03)
    val ASPECT_PLAYER_DROPPED = DungeonDropEffect(glowColor = ItemColor.ASPECT)
}