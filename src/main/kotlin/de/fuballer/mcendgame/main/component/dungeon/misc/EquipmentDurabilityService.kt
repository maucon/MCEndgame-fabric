package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.messaging.misc.DamageItemStackCommand
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import kotlin.math.min

private const val MAX_DURABILITY_DAMAGE = 3

@Injectable
class EquipmentDurabilityService {
    @CommandHandler
    fun on(cmd: DamageItemStackCommand) {
        if (!cmd.world.isDungeonWorld()) return
        cmd.damage = min(cmd.damage, MAX_DURABILITY_DAMAGE)
    }
}