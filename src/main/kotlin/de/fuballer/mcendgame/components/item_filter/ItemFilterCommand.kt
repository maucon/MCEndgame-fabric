package de.fuballer.mcendgame.components.item_filter

import com.mojang.brigadier.context.CommandContext
import de.fuballer.mcendgame.functional.command.ChatCommand
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.server.command.ServerCommandSource

@Injectable
class ItemFilterCommand(
    private val itemFilterService: ItemFilterService,
) : ChatCommand() {
    override val name = "filter"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val player = context.source.player ?: return
        itemFilterService.openFilterInventory(player)
    }
}