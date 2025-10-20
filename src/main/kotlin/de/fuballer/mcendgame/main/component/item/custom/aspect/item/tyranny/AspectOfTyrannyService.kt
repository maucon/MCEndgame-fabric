package de.fuballer.mcendgame.main.component.item.custom.aspect.item.tyranny

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateEnemiesCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object AspectOfTyrannyService {
    @CommandHandler
    fun generateDungeonEnemies(cmd: DungeonGenerateEnemiesCommand) {
        if (cmd.isEncounter) return
        val count = cmd.aspects[AspectItems.ASPECT_OF_TYRANNY] ?: return

        val eliteSpawnPositions = cmd.spawnPositions.shuffled().take(AspectOfTyranny.ADDITIONAL_ELITES * count)
        cmd.eliteSpawnPositions.addAll(eliteSpawnPositions)
    }
}