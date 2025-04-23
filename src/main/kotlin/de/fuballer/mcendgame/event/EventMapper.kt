package de.fuballer.mcendgame.event

import de.fuballer.mcendgame.accessor.MobEntityBossAccessor
import de.fuballer.mcendgame.event.dungeon.DungeonBossDeathEvent
import de.fuballer.mcendgame.event.server.ServerStartedEvent
import de.fuballer.mcendgame.event.server.ServerStoppingEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents

@Injectable
object EventMapper {
    @EventSubscriber
    fun on(event: LivingEntityDeathEvent) {
        val entity = event.entity

        val accessor = entity as? MobEntityBossAccessor ?: return
        if (!accessor.`mcendgame$isDungeonBoss`()) return

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
}