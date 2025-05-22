package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamageCommand
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isDungeonEnemy
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class IgnoredDamageService {
    @CommandHandler
    fun on(cmd: LivingEntityDamageCommand) {
        if (!cmd.world.isDungeonWorld()) return

        if (!cmd.entity.isDungeonEnemy()) return
        if (cmd.damageSource.attacker?.isDungeonEnemy() != true) return

        cmd.dealsDamage = false
    }
}