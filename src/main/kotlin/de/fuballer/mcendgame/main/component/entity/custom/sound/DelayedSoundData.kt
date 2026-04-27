package de.fuballer.mcendgame.main.component.entity.custom.sound

import net.minecraft.entity.Entity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent

data class DelayedSoundData(
    private val sound: SoundEvent,
    private val volume: () -> Float,
    private val pitch: () -> Float,
    private val category: SoundCategory,
    val delay: Int = 0,
) {
    constructor(
        sound: SoundEvent,
        volume: Float,
        pitch: Float,
        category: SoundCategory,
        delay: Int = 0,
    ) : this(sound, { volume }, { pitch }, category, delay)

    fun getInstance() = DelayedSoundInstance(this)

    fun play(world: ServerWorld, entity: Entity) {
        world.playSound(entity, entity.blockPos, sound, category, volume(), pitch())
    }
}