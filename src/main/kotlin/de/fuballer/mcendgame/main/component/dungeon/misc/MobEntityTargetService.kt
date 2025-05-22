package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.messaging.misc.MobEntityTargetCommand
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isDungeonEnemy
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class MobEntityTargetService {
    @CommandHandler
    fun on(cmd: MobEntityTargetCommand) {
        if (!cmd.world.isDungeonWorld()) return

        if (!cmd.entity.isDungeonEnemy()) return
        if (cmd.target?.isDungeonEnemy() != true) return

        cmd.canTarget = false
    }
}