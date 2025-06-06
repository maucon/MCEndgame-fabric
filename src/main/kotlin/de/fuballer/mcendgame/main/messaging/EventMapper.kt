package de.fuballer.mcendgame.main.messaging

import de.fuballer.mcendgame.main.messaging.dungeon.DungeonBossDeathEvent
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEntityDeathEvent
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDeathEvent
import de.fuballer.mcendgame.main.messaging.server.ServerEndTickEvent
import de.fuballer.mcendgame.main.messaging.server.ServerStartedEvent
import de.fuballer.mcendgame.main.messaging.server.ServerStoppingEvent
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isDungeonBoss
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents

@Injectable
object EventMapper {
    @EventSubscriber
    fun on(event: LivingEntityDeathEvent) {
        val entity = event.entity
        if (!entity.world.isDungeonWorld()) return

        val dungeonEntityDeathEvent = DungeonEntityDeathEvent(event.isClient, event.world, event.entity, event.killer)
        EventGateway.launchPublish(dungeonEntityDeathEvent)
    }

    @EventSubscriber
    fun on(event: DungeonEntityDeathEvent) {
        val entity = event.entity

        if (!entity.isDungeonBoss()) return

        val dungeonBossDeathEvent = DungeonBossDeathEvent(event.isClient, event.world, event.entity, event.killer)
        EventGateway.launchPublish(dungeonBossDeathEvent)
    }

    @Initializer
    fun onServerStarted() = ServerLifecycleEvents.SERVER_STARTED.register {
        val event = ServerStartedEvent(it)
        EventGateway.launchPublish(event)
    }

    @Initializer
    fun onServerStopping() = ServerLifecycleEvents.SERVER_STOPPING.register {
        val event = ServerStoppingEvent(it)
        EventGateway.launchPublish(event)
    }

    @Initializer
    fun onServerTickEnd() = ServerTickEvents.END_SERVER_TICK.register {
        val event = ServerEndTickEvent(it)
        EventGateway.launchPublish(event)
    }
}