package de.fuballer.mcendgame.main.component.entity.custom.sound

import net.minecraft.entity.Entity
import net.minecraft.server.world.ServerWorld

data class DelayedSoundInstance(
    val soundData: DelayedSoundData,
) {
    var age = 0

    fun tick(
        world: ServerWorld,
        entity: Entity,
    ): Boolean {
        if (shouldCancel(entity)) return true

        age++
        if (age < soundData.delay) return false

        soundData.play(world, entity)
        return true
    }

    fun shouldCancel(entity: Entity) = !entity.isAlive
}