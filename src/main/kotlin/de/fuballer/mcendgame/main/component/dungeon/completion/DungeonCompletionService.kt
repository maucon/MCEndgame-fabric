package de.fuballer.mcendgame.main.component.dungeon.completion

import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorld.Companion.toDungeonWorld
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonBossDeathEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.server.world.ServerWorld

@Injectable
class DungeonCompletionService {
    @EventSubscriber
    fun on(event: DungeonBossDeathEvent) {
        val world = event.world
        if (world !is ServerWorld) return

        val dungeonWorld = world.toDungeonWorld()
        val players = world.players.toList()

        if (dungeonWorld.isCompleted) return
        dungeonWorld.setCompleted()

        val dungeonCompletedEvent = DungeonCompletedEvent(event.isClient, dungeonWorld, players)
        EventGateway.launchPublish(dungeonCompletedEvent)
    }
}