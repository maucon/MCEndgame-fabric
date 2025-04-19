package de.fuballer.mcendgame.components.item_filter

import com.mojang.brigadier.Command
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager

@Injectable
class ItemFilterCommandRegisterer(
    private val itemFilterService: ItemFilterService,
) {
    @Initializer
    fun init() {
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
            dispatcher.register(
                CommandManager.literal("filter")
                    .executes { context ->
                        val player = context.source.player ?: return@executes Command.SINGLE_SUCCESS
                        itemFilterService.openFilterInventory(player)
                        Command.SINGLE_SUCCESS
                    })
        })
    }
}