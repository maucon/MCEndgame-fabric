package de.fuballer.mcendgame.main.component.item.custom.aspect.item.aspect_of_greed

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateEnemiesCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object AspectOfGreedService {
    @CommandHandler
    fun generateDungeonEnemies(cmd: DungeonGenerateEnemiesCommand) {
        val count = cmd.aspects[AspectItems.ASPECT_OF_GREED] ?: return

        val lootGoblinSpawnPositions = cmd.spawnPositions.shuffled().take(AspectOfGreed.ADDITIONAL_LOOT_GOBLINS * count)
        cmd.lootGoblinSpawnPositions.addAll(lootGoblinSpawnPositions)
    }
}