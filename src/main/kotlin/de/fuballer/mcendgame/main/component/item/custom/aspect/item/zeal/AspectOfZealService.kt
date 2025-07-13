package de.fuballer.mcendgame.main.component.item.custom.aspect.item.zeal

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.messaging.dungeon.DungeonPlayerIncreaseProgressCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
object AspectOfZealService {
    @CommandHandler
    fun onPlayerIncreaseProgress(cmd: DungeonPlayerIncreaseProgressCommand) {
        val count = cmd.aspects[AspectItems.ASPECT_OF_ZEAL] ?: return
        cmd.progressGranted += count * AspectOfZeal.ADDITIONAL_PROGRESS
    }
}