package de.fuballer.mcendgame.components.dungeon.world

import de.fuballer.mcendgame.components.dungeon.world.db.DungeonWorldEntity
import de.fuballer.mcendgame.components.dungeon.world.db.DungeonWorldRepository
import de.fuballer.mcendgame.components.scheduler.Scheduler
import de.fuballer.mcendgame.configuration.RuntimeConfig
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.di.annotation.Logging
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import org.slf4j.Logger

@Injectable
class DungeonWorldService(
    @Logging private val log: Logger,
    private val dungeonWorldRepo: DungeonWorldRepository,
    private val scheduler: Scheduler
) {
    @Initializer
    fun onServerStarted() = ServerLifecycleEvents.SERVER_STARTED.register {
        scheduler.repeating(DungeonWorldSettings.EMPTY_WORLD_CHECK_PERIOD, ::deleteEmptyWorlds)
    }

    @Initializer
    fun onServerStopping() = ServerLifecycleEvents.SERVER_STOPPING.register {
        dungeonWorldRepo.findAll()
            .forEach { deleteWorld(it) }
    }

    fun create(player: PlayerEntity): ServerWorld {
        val world = RuntimeConfig.FANTASY
            .openTemporaryWorld(DungeonWorldSettings.generateIdentifier(), DungeonWorldSettings.WORLD_CONFIG)
            .asWorld()

        val entity = DungeonWorldEntity(player, world)
        dungeonWorldRepo.save(entity)

        return world
    }

    private fun deleteEmptyWorlds() {
        log.info("Checking for empty worlds")

        dungeonWorldRepo.findAll()
            .map {
                updateDeleteTimer(it)
                dungeonWorldRepo.save(it)
            }
            .filter { it.emptySince.plus(DungeonWorldSettings.MAX_EMPTY_TIME, DateTimeUnit.SECOND) < Clock.System.now() }
            .forEach {
                log.warn("Dungeon world '${it.world.registryKey.value}' was empty for too long, deleting it!")
                deleteWorld(it)
            }
    }

    private fun updateDeleteTimer(entity: DungeonWorldEntity) {
        if (entity.world.players.isNotEmpty()) {
            entity.emptySince = Clock.System.now()
        }
    }

    private fun deleteWorld(entity: DungeonWorldEntity) {
        RuntimeConfig.FANTASY.tickDeleteWorld(entity.world) // is this enough?
        dungeonWorldRepo.delete(entity)
    }
}