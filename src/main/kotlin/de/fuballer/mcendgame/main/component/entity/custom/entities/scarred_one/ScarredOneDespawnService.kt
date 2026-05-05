package de.fuballer.mcendgame.main.component.entity.custom.entities.scarred_one

import de.fuballer.mcendgame.main.functional.scheduler.Scheduler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.Entity.RemovalReason
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import kotlin.random.Random

private const val DURATION = 40
private const val SOUND_DELAY = 15

@Injectable
class ScarredOneDespawnService(
    val scheduler: Scheduler,
) {
    @EventSubscriber(sync = true)
    fun onDespawn(event: ScarredOneDespawnEvent) {
        val entity = event.entity
        val world = entity.entityWorld as? ServerWorld ?: return

        spawnParticles(world, entity, event.accepted)
        playSound(world, entity, event.accepted)
        despawn(entity)
    }

    private fun spawnParticles(
        world: ServerWorld,
        entity: ScarredOneEntity,
        accepted: Boolean,
    ) {
        scheduler.repeatingForDuration(1, DURATION) { tick ->
            world.spawnParticles(
                ParticleTypes.WHITE_SMOKE,
                entity.x,
                entity.y + entity.height / 2,
                entity.z,
                if (tick < DURATION) 1 + if (Random.nextDouble() < tick / DURATION) 1 else 0 else 20,
                0.25,
                0.6,
                0.25,
                if (tick < DURATION) (0.04 / DURATION) * tick else 0.08
            )
        }

        val particleType = if (accepted) ParticleTypes.HAPPY_VILLAGER else ParticleTypes.ANGRY_VILLAGER
        val horizontalSpread = if (accepted) 0.5 else 0.3
        scheduler.delayed(SOUND_DELAY) {
            world.spawnParticles(
                particleType,
                entity.eyePos.x,
                entity.eyePos.y,
                entity.eyePos.z,
                5,
                horizontalSpread,
                0.25,
                horizontalSpread,
                0.1
            )
        }
    }

    private fun playSound(
        world: ServerWorld,
        entity: ScarredOneEntity,
        accepted: Boolean,
    ) {
        val sound = if (accepted) SoundEvents.ENTITY_VILLAGER_YES else SoundEvents.ENTITY_VILLAGER_NO
        scheduler.delayed(SOUND_DELAY) {
            world.playSound(entity, entity.blockPos, sound, SoundCategory.NEUTRAL, 1F, 0.75F + 0.15F * Random.nextFloat())
        }

        scheduler.delayed(DURATION) {
            world.playSound(entity, entity.blockPos, SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 1F, 0.95F + 0.1F * Random.nextFloat())
        }
    }

    private fun despawn(
        entity: ScarredOneEntity,
    ) {
        scheduler.delayed(DURATION) { entity.remove(RemovalReason.DISCARDED) }
    }
}