package de.fuballer.mcendgame.main.component.item.custom.aspect.item.eminence

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateEnemiesCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object AspectOfEminenceService {
    @CommandHandler
    fun on(cmd: DungeonGenerateEnemiesCommand) {
        val count = cmd.aspects[AspectItems.ASPECT_OF_EMINENCE] ?: return
        repeat(count) { cmd.additionalAttributeProbabilities.add(AspectOfEminence.ADDITIONAL_ATTRIBUTE_PROBABILITY) }
    }
}