package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.messaging.misc.EntityConversionCommand
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class EntityConversionService {
    @CommandHandler
    fun on(cmd: EntityConversionCommand) {
        if (!cmd.world.isDungeonWorld()) return
        cmd.canConvert = false
    }
}