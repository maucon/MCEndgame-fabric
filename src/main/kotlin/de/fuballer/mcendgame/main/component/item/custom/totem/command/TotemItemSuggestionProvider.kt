package de.fuballer.mcendgame.main.component.item.custom.totem.command

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import de.fuballer.mcendgame.main.component.item.custom.totem.TotemItemRegistry
import net.minecraft.server.command.ServerCommandSource
import java.util.concurrent.CompletableFuture

class TotemItemSuggestionProvider : SuggestionProvider<ServerCommandSource> {
    override fun getSuggestions(
        context: CommandContext<ServerCommandSource>,
        builder: SuggestionsBuilder
    ): CompletableFuture<Suggestions> {
        for (name in TotemItemRegistry.NAME_MAP.keys) {
            builder.suggest(name)
        }

        return builder.buildFuture()
    }
}