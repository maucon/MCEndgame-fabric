package de.fuballer.mcendgame.main.component.item.custom.aspect.item.impatience

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonGenerateCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object AspectOfImpatienceService {
    @CommandHandler
    fun onGenerateLayout(cmd: DungeonGenerateCommand) {
        val count = cmd.aspects[AspectItems.ASPECT_OF_IMPATIENCE] ?: return
        cmd.dungeonLevel += count * AspectOfImpatience.ADDITIONAL_LEVELS
    }
}