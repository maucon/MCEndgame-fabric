package de.fuballer.mcendgame.components.item_filter

import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.Items

@Injectable
class ItemFilterService {
    @CommandHandler
    fun on(cmd: PlayerItemPickupCommand) {
        if (cmd.item == Items.DIAMOND) {
            cmd.cancel()
        }
    }
}