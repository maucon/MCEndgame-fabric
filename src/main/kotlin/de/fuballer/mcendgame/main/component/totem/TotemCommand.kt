package de.fuballer.mcendgame.main.component.totem

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

private const val NAME = "totems"

@Injectable
class TotemCommand(
    private val totemService: TotemService,
) {
    @Initializer
    fun register() = CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
        dispatcher.register(
            CommandManager.literal(NAME)
                .executes { context -> execute(context) }
        )
    })

    fun execute(
        context: CommandContext<ServerCommandSource>,
    ): Int {
        val player = context.source.player ?: return 0
        totemService.openInventory(player)
        return Command.SINGLE_SUCCESS
    }
}