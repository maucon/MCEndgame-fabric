package de.fuballer.mcendgame.main.component.dungeon.completion

import de.fuballer.mcendgame.main.accessor.DungeonWorldAccessor
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonBossDeathEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber

@Injectable
class DungeonCompletionService {
    @EventSubscriber
    fun on(event: DungeonBossDeathEvent) {
        val world = event.world
        val players = world.players.toList()

        val dungeonWorld = world as? DungeonWorldAccessor ?: return
        if (dungeonWorld.`mcendgame$isCompleted`()) return

        val dungeonLevel = dungeonWorld.`mcendgame$getLevel`()
        dungeonWorld.`mcendgame$setCompleted`()

        val dungeonCompletedEvent = DungeonCompletedEvent(event.isClient, world, players, dungeonLevel)
        EventGateway.launchPublish(dungeonCompletedEvent)
    }
}