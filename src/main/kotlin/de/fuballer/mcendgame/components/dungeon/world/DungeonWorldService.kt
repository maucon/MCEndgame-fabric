package de.fuballer.mcendgame.components.dungeon.world

import de.fuballer.mcendgame.components.dungeon.world.db.DungeonWorldEntity
import de.fuballer.mcendgame.components.dungeon.world.db.DungeonWorldRepository
import de.fuballer.mcendgame.components.scheduler.Scheduler
import de.fuballer.mcendgame.configuration.RuntimeConfig
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import de.maucon.mauconframework.annotation.Logging
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World
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

    @Initialize // FIXME remove
    fun test() = AttackEntityCallback.EVENT.register { player: PlayerEntity, world: World, hand: Hand, entity1: Entity, entityHitResult: EntityHitResult? ->
        if (hand != Hand.MAIN_HAND) return@register ActionResult.PASS

        val dungeonWorld = createWorld()

        player.teleport(dungeonWorld, 0.0, 100.0, 0.0, setOf(), 0.0f, 0.0f, false)

        val entity = DungeonWorldEntity(player, dungeonWorld)
        dungeonWorldRepo.save(entity)

        return@register ActionResult.PASS
    }

    fun createWorld(): ServerWorld {
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