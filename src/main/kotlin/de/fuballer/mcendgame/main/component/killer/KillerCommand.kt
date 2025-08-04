package de.fuballer.mcendgame.main.component.killer

import com.mojang.brigadier.context.CommandContext
import de.fuballer.mcendgame.main.functional.command.SimpleChatCommand
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.server.command.ServerCommandSource

@Injectable
class KillerCommand(
    private val killerService: KillerService,
) : SimpleChatCommand() {
    override val name = "killer"

    override fun execute(context: CommandContext<ServerCommandSource>) {
        val player = context.source.player ?: return
        killerService.openKillerInventory(player)
    }
}