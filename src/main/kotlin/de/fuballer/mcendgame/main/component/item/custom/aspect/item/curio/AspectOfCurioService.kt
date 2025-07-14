package de.fuballer.mcendgame.main.component.item.custom.aspect.item.curio

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateEnemiesCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object AspectOfCurioService {
    @CommandHandler
    fun onGenerateEnemies(cmd: DungeonGenerateEnemiesCommand) {
        val count = cmd.aspects[AspectItems.ASPECT_OF_CURIO] ?: return
        val increasedUniques = count * AspectOfCurio.INCREASED_UNIQUES
        cmd.uniqueEquipmentProbability *= 1 + increasedUniques
    }
}