package de.fuballer.mcendgame.components.item_filter

import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.Item
import java.util.*

@Injectable
class ItemFilterService {
    private val playerFilter = mutableMapOf<UUID, List<Item>>()

    @CommandHandler
    fun on(cmd: PlayerItemPickupCommand) {
        val uuid = cmd.player.uuid
        val filter = playerFilter[uuid] ?: return
        if (filter.contains(cmd.item)) cmd.cancel()
    }
}