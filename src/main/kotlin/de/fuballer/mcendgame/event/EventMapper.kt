package de.fuballer.mcendgame.event

import de.fuballer.mcendgame.accessor.MobEntityBossAccessor
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventGateway
import de.maucon.mauconframework.event.EventSubscriber

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
}