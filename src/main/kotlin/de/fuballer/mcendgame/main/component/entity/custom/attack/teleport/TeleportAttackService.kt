package de.fuballer.mcendgame.main.component.entity.custom.attack.teleport

import de.fuballer.mcendgame.main.component.entity.custom.interfaces.TeleportAttackMob
import de.fuballer.mcendgame.main.functional.scheduler.Scheduler
import de.fuballer.mcendgame.main.util.extension.EntityExtension.centerPos
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.Entity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import kotlin.math.max

private const val PREPARATION_PARTICLE_DURATION = 40 // ticks

@Injectable
class TeleportAttackService<T>(
    val scheduler: Scheduler,
) where T : Entity, T : TeleportAttackMob {
    @EventSubscriber
    fun on(event: TeleportAttackEvent<T>) {
        val attacker = event.attacker
        val target = event.target

        scheduler.simple(max(0, event.chosePositionDelayTicks - PREPARATION_PARTICLE_DURATION)) { createPreparationParticles(attacker) }
        scheduler.simple(event.chosePositionDelayTicks) { choseTeleportPosition(attacker, target) }

        scheduler.simple(event.teleportDelayTicks) {
            teleport(attacker)
            createArriveParticles(attacker)
        }
    }

    private fun choseTeleportPosition(attacker: T, target: Entity) {
        if (attacker.world != target.world) return
        attacker.teleportAttackTargetPosition = target.pos
    }

    private fun teleport(attacker: T) {
        val pos = attacker.teleportAttackTargetPosition ?: return
        attacker.refreshPositionAfterTeleport(pos.x, pos.y, pos.z)
        attacker.teleportAttackTargetPosition = null
    }

    private fun createPreparationParticles(attacker: Entity) {
        val world = attacker.world as? ServerWorld ?: return
        val pos = attacker.centerPos()
        world.spawnParticles(
            ParticleTypes.PORTAL,
            pos.x,
            pos.y,
            pos.z,
            50,
            0.1,
            0.1,
            0.1,
            1.0
        )
    }

    private fun createArriveParticles(attacker: Entity) {
        val world = attacker.world as? ServerWorld ?: return
        val pos = attacker.centerPos()
        world.spawnParticles(
            ParticleTypes.REVERSE_PORTAL,
            pos.x,
            pos.y,
            pos.z,
            50,
            0.1,
            0.1,
            0.1,
            1.0
        )
    }
}