package de.fuballer.mcendgame.components.item_filter

import com.mojang.brigadier.Command
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.screen.GenericContainerScreenHandler
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.server.command.CommandManager
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

@Injectable
class ItemFilterCommandRegisterer {
    @Initializer
    fun init() {
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
            dispatcher.register(
                CommandManager.literal("filter")
                    .executes { context ->
                        val player = context.source.player ?: return@executes Command.SINGLE_SUCCESS
                        openFilterInventory(player)
                        Command.SINGLE_SUCCESS
                    })
        })
    }

    private fun openFilterInventory(player: ServerPlayerEntity) {
        val title = Text.translatable("container.mcendgame.filter.title")
        val screenHandlerFactory = SimpleNamedScreenHandlerFactory({ syncId, pInv, _ ->
            GenericContainerScreenHandler.createGeneric9x1(syncId, pInv)
        }, title)

        player.openHandledScreen(screenHandlerFactory)
    }
}