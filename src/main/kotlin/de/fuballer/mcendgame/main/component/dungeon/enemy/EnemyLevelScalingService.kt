package de.fuballer.mcendgame.main.component.dungeon.enemy

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.addCustomAttributes
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonEnemiesGeneratedCommand
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.isDungeonBoss
import de.fuballer.mcendgame.main.util.extension.mixin.WorldMixinExtension.getDungeonLevel
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class EnemyLevelScalingService {
    @CommandHandler
    fun on(command: DungeonEnemiesGeneratedCommand) {
        val level = command.world.getDungeonLevel()

        val levelAttributes = EnemyLevelScalingSettings.getEnemyLevelAttributes(level)
        val enemies = command.enemies
        enemies.forEach {
            it.addCustomAttributes(levelAttributes)
            it.health = it.maxHealth
        }

        val bosses = enemies.filter { it.isDungeonBoss() }
        if (bosses.isEmpty()) return
        val bossAttributes = EnemyLevelScalingSettings.getBossLevelAttributes(level)
        bosses.forEach {
            it.addCustomAttributes(bossAttributes)
            it.health = it.maxHealth
        }
    }
}