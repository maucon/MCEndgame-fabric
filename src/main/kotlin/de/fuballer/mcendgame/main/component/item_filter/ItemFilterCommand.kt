package de.fuballer.mcendgame.main.component.item_filter

import com.mojang.brigadier.context.CommandContext
import de.fuballer.mcendgame.main.functional.command.SimpleChatCommand
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.server.command.ServerCommandSource

@Injectable
class ItemFilterCommand(
    private val itemFilterService: ItemFilterService,
) : SimpleChatCommand() {
    override val name = "dungeon-filter"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val player = context.source.player ?: return
        itemFilterService.openFilterInventory(player)
    }
}