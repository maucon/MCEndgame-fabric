package de.fuballer.mcendgame.components.dungeon.world

import de.fuballer.mcendgame.components.dungeon.world.db.DungeonWorldEntity
import de.fuballer.mcendgame.components.dungeon.world.db.DungeonWorldRepository
import de.fuballer.mcendgame.components.scheduler.Scheduler
import de.fuballer.mcendgame.configuration.RuntimeConfig
import de.fuballer.mcendgame.event.DungeonOpenEvent
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import de.maucon.mauconframework.annotation.Logging
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.server.world.ServerWorld
import org.slf4j.Logger

@Injectable
class DungeonWorldService(
    @Logging private val log: Logger,
    private val dungeonWorldRepo: DungeonWorldRepository,
    private val scheduler: Scheduler
) {
    @Initialize
    fun init() = ServerLifecycleEvents.SERVER_STARTED.register {
        scheduler.repeating(DungeonWorldSettings.EMPTY_WORLD_CHECK_PERIOD, ::deleteEmptyWorlds)
    }

    @Initialize
    fun on() = DungeonOpenEvent.NOTIFIER.listen { event ->
        println(event)

        val dungeonWorld = createWorld()

        event.player.teleport(dungeonWorld, 0.0, 100.0, 0.0, setOf(), 0.0f, 0.0f, false)

        val entity = DungeonWorldEntity(event.player, dungeonWorld)
        dungeonWorldRepo.save(entity)
    }

    private fun createWorld(): ServerWorld {
        return RuntimeConfig.FANTASY
            .openTemporaryWorld(DungeonWorldSettings.generateIdentifier(), DungeonWorldSettings.WORLD_CONFIG)
            .asWorld()
    }

    private fun deleteEmptyWorlds() {
        log.info("Checking for empty worlds")

        dungeonWorldRepo.findAll()
            .map {
                updateDeleteTimer(it)
                dungeonWorldRepo.save(it)
            }
            .filter { it.emptySince.plus(DungeonWorldSettings.MAX_EMPTY_TIME, DateTimeUnit.SECOND) < Clock.System.now() }
            .forEach { deleteWorld(it) }
    }

    private fun updateDeleteTimer(entity: DungeonWorldEntity) {
        if (entity.world.players.isNotEmpty()) {
            entity.emptySince = Clock.System.now()
        }
    }

    private fun deleteWorld(entity: DungeonWorldEntity) {
        log.warn("Dungeon world '${entity.world.registryKey.value}' was empty for too long, deleting it!")

        RuntimeConfig.FANTASY.tickDeleteWorld(entity.world) // is this enough?
        dungeonWorldRepo.delete(entity)
    }
}