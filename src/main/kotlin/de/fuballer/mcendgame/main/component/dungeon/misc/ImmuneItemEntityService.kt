package de.fuballer.mcendgame.main.component.dungeon.misc

import de.fuballer.mcendgame.main.messaging.misc.ItemEntityDamagedCommand
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.damage.DamageTypes

@Injectable
class ImmuneItemEntityService {
    @CommandHandler
    fun on(cmd: ItemEntityDamagedCommand) {
        if (!cmd.world.isDungeonWorld()) return
        if (cmd.source.typeRegistryEntry == DamageTypes.OUT_OF_WORLD) return

        cmd.ignoresDamage = true
    }
}