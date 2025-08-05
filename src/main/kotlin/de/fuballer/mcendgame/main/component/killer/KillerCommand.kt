package de.fuballer.mcendgame.main.component.killer

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Formatting

private const val NAME = "killer"
private const val PLAYER_ARGUMENT = "player"
private const val NO_KILLER_KEY = "error.mcendgame.no_killer_found"

@Injectable
class KillerCommand(
    private val killerService: KillerService,
) {
    @Initializer
    fun register() = CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
        dispatcher.register(
            CommandManager.literal(NAME)
                .executes { context -> execute(context) }
                .then(
                    CommandManager.argument(PLAYER_ARGUMENT, EntityArgumentType.player())
                        .executes { context ->
                            val killed = EntityArgumentType.getPlayer(context, PLAYER_ARGUMENT)
                            execute(context, killed)
                        }
                )
        )
    })

    fun execute(
        context: CommandContext<ServerCommandSource>,
        killed: ServerPlayerEntity? = null,
    ): Int {
        val player = context.source.player ?: return 0
        val killedPlayer = killed ?: player

        if (!killerService.openKillerInventory(player, killedPlayer)) {
            player.sendMessage(Text.translatable(NO_KILLER_KEY, killedPlayer.name).formatted(Formatting.RED))
            return 0
        }

        return Command.SINGLE_SUCCESS
    }
}