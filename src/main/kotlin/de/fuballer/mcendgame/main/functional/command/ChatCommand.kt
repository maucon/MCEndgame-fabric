package de.fuballer.mcendgame.main.functional.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

abstract class ChatCommand {
    abstract val name: String
    abstract fun execute(context: CommandContext<ServerCommandSource>)

    @Initializer
    fun register() = CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
        dispatcher.register(
            CommandManager.literal(name)
                .executes {
                    execute(it)
                    return@executes Command.SINGLE_SUCCESS
                }
        )
    })
}