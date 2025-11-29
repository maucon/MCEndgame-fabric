package de.fuballer.mcendgame.main.component.killer

import com.mojang.authlib.GameProfile
import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.command.argument.GameProfileArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import net.minecraft.util.Formatting

private const val NAME = "killer"
private const val GAME_PROFILE_ARGUMENT = "game_profile"
private const val NO_KILLER_KEY = "commands.mcendgame.killer.no_killer"

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
                    CommandManager.argument(GAME_PROFILE_ARGUMENT, GameProfileArgumentType.gameProfile())
                        .executes { context ->
                            val profiles = GameProfileArgumentType.getProfileArgument(context, GAME_PROFILE_ARGUMENT)
                            val profile = if (profiles.isNotEmpty()) profiles.first() else null
                            execute(context, profile)
                        }
                )
        )
    })

    fun execute(
        context: CommandContext<ServerCommandSource>,
        killedProfile: GameProfile? = null,
    ): Int {
        val player = context.source.player ?: return 0
        val killedUUID = killedProfile?.id ?: player.uuid

        if (!killerService.openKillerInventory(player, killedUUID)) {
            val name = killedProfile?.name ?: player.name
            player.sendMessage(Text.translatable(NO_KILLER_KEY, name).formatted(Formatting.RED))
            return 0
        }

        return Command.SINGLE_SUCCESS
    }
}