package de.fuballer.mcendgame.main.component.dungeon.enemy

import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEnemiesGeneratedEvent
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.addCustomAttributes
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.isDungeonBoss
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getDungeonLevel
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber

@Injectable
class EnemyLevelScalingService {
    @EventSubscriber
    fun on(event: DungeonEnemiesGeneratedEvent) {
        val level = event.world.getDungeonLevel()

        val levelAttributes = EnemyLevelScalingSettings.getEnemyLevelAttributes(level)
        val enemies = event.enemies
        enemies.forEach { it.addCustomAttributes(levelAttributes) }

        val bosses = enemies.filter { it.isDungeonBoss() }
        if (bosses.isEmpty()) return
        val bossAttributes = EnemyLevelScalingSettings.getBossLevelAttributes(level)
        bosses.forEach { it.addCustomAttributes(bossAttributes) }
    }
}