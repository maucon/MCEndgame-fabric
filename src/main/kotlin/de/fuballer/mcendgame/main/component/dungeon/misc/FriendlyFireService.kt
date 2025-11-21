package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isEnemy
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class FriendlyFireService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        if (!cmd.world.isDungeonWorld()) return
        val damager = cmd.damager ?: return
        if (cmd.damaged.isEnemy(damager)) return

        //TODO #128 don't deal damage
    }
}