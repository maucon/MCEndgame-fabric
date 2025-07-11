package de.fuballer.mcendgame.main.component.dungeon.world

import de.fuballer.mcendgame.main.accessor.DungeonWorldAccessor
import de.fuballer.mcendgame.main.component.dungeon.world.db.DungeonWorldEntity
import de.fuballer.mcendgame.main.component.dungeon.world.db.DungeonWorldRepository
import de.fuballer.mcendgame.main.configuration.RuntimeConfig
import de.fuballer.mcendgame.main.functional.scheduler.Scheduler
import de.fuballer.mcendgame.main.messaging.server.ServerStartedEvent
import de.fuballer.mcendgame.main.messaging.server.ServerStoppingEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.di.annotation.Logging
import de.maucon.mauconframework.event.EventSubscriber
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import org.slf4j.Logger

@Injectable
class DungeonWorldService(
    @Logging private val log: Logger,
    private val dungeonWorldRepo: DungeonWorldRepository,
    private val scheduler: Scheduler
) {
    @EventSubscriber
    fun on(event: ServerStartedEvent) {
        scheduler.repeating(DungeonWorldSettings.EMPTY_WORLD_CHECK_PERIOD, ::deleteEmptyWorlds)
    }

    @EventSubscriber
    fun on(event: ServerStoppingEvent) {
        dungeonWorldRepo.findAll()
            .forEach { deleteWorld(it) }
    }

    fun create(player: PlayerEntity, dungeonLevel: Int): ServerWorld {
        val world = RuntimeConfig.FANTASY
            .openTemporaryWorld(DungeonWorldSettings.generateIdentifier(), DungeonWorldSettings.WORLD_CONFIG)
            .asWorld()

        val dungeonWorld = world as DungeonWorldAccessor
        dungeonWorld.`mcendgame$setLevel`(dungeonLevel)

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