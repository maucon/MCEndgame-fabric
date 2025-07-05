package de.fuballer.mcendgame.main.component.item.custom.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.UniqueAttributesItem
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.item.Item
import net.minecraft.server.command.ServerCommandSource

@Injectable
class GiveUniqueItemService {
    fun giveUniqueItem(context: CommandContext<ServerCommandSource>, hasRolls: Boolean): Int {
        val player = context.source.player ?: return 0
        val uniqueItem = context.getArgument(GiveUniqueItemCommand.UNIQUE_ITEM_ARGUMENT, Item::class.java) as? UniqueAttributesItem ?: return 0
        val rolls = if (!hasRolls) listOf()
        else context.getArgument(GiveUniqueItemCommand.DOUBLE_ROLLS_ARGUMENT, String::class.java).split(" ").mapNotNull { it.toDoubleOrNull() }

        player.giveItemStack(uniqueItem.getRolledStack(rolls))

        return Command.SINGLE_SUCCESS
    }
}