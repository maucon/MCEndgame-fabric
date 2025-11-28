package de.fuballer.mcendgame.main.component.item.custom.totem.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.context.CommandContext
import de.fuballer.mcendgame.main.component.item.custom.totem.TotemItem
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.item.Item
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import net.minecraft.util.Colors

@Injectable
class GiveTotemItemCommand {
    companion object {
        private const val NAME = "givetotem"
        private const val TOTEM_ITEM_ARGUMENT = "totem-item"
        private const val TIER_ARGUMENT = "tier"
    }

    @Initializer
    fun register() = CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, _, _ ->
        dispatcher.register(
            CommandManager.literal(NAME)
                .requires { it.hasPermissionLevel(2) }
                .then(
                    CommandManager.argument(TOTEM_ITEM_ARGUMENT, TotemItemArgumentType())
                        .suggests(TotemItemSuggestionProvider())
                        .executes { giveTotemItem(it, false) }
                        .then(
                            CommandManager.argument(TIER_ARGUMENT, IntegerArgumentType.integer(0))
                                .executes { giveTotemItem(it, true) }
                        )
                )
        )
    })

    private fun giveTotemItem(
        context: CommandContext<ServerCommandSource>,
        hasSpecifiedTier: Boolean,
    ): Int {
        val player = context.source.player ?: return 0
        val totemItem = context.getArgument(TOTEM_ITEM_ARGUMENT, Item::class.java)
        if (totemItem !is TotemItem) return 0

        val tier = if (hasSpecifiedTier) context.getArgument(TIER_ARGUMENT, Int::class.java) else 0
        if (totemItem.maxTier < tier) {
            player.sendMessage(Text.translatable("error.mcendgame.invalid_totem_tier", totemItem.maxTier).withColor(Colors.LIGHT_RED))
            return 0
        }

        val stack = totemItem.getStack(tier)
        player.giveItemStack(stack)

        return Command.SINGLE_SUCCESS
    }
}