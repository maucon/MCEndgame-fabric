package de.fuballer.mcendgame.main.component.damage.ignore_damage

import de.fuballer.mcendgame.main.util.extension.EntityExtension.isAlly
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.isDungeonEnemy
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class DungeonAllyFriendlyFireService {
    @CommandHandler
    fun on(cmd: IgnoreDamageCommand) {
        if (!cmd.world.isDungeonWorld()) return
        if (!cmd.entity.isDungeonEnemy()) return

        val attacker = cmd.damageSource.attacker ?: return
        if (!cmd.entity.isAlly(attacker)) return

        cmd.ignoreDamage = true
    }
}