package de.fuballer.mcendgame.main.component.item.custom.aspect.item.hordes

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateEnemiesCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object AspectOfHordesService {
    @CommandHandler
    fun generateDungeonEnemies(cmd: DungeonGenerateEnemiesCommand) {
        val count = cmd.aspects[AspectItems.ASPECT_OF_HORDES] ?: return

        val increasedEnemies = count * AspectOfHordes.INCREASED_ENEMIES
        val additionalEnemiesCount = (cmd.spawnPositions.size * increasedEnemies).toInt()

        val shuffledSpawnPositions = cmd.spawnPositions.shuffled()
        val additionalSpawnPositions = List(additionalEnemiesCount) { index -> shuffledSpawnPositions[index % shuffledSpawnPositions.size] }
        cmd.spawnPositions.addAll(additionalSpawnPositions)
    }
}