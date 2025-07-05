package de.fuballer.mcendgame.main.component.item.custom.command

import com.mojang.brigadier.arguments.StringArgumentType
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager

@Injectable
class GiveUniqueItemCommand(
    private val giveUniqueItemService: GiveUniqueItemService,
) {
    companion object {
        private const val NAME = "give-unique"
        const val UNIQUE_ITEM_ARGUMENT = "unique-item"
        const val DOUBLE_ROLLS_ARGUMENT = "double-roll"
    }

    @Initializer
    fun register() = CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
        dispatcher.register(
            CommandManager.literal(NAME)
                .then(
                    CommandManager.argument(UNIQUE_ITEM_ARGUMENT, UniqueItemArgumentType())
                        .suggests(UniqueItemSuggestionProvider())
                        .executes { giveUniqueItemService.giveUniqueItem(it, false) }
                        .then(
                            CommandManager.argument(DOUBLE_ROLLS_ARGUMENT, StringArgumentType.greedyString())
                                .executes { giveUniqueItemService.giveUniqueItem(it, true) }
                        )
                )
        )
    })
}