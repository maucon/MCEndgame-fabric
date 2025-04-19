package de.fuballer.mcendgame.components.item_filter

import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.Item
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.Text
import java.util.*

@Injectable
class ItemFilterService {
    private val playerFilter = mutableMapOf<UUID, Set<Item>>()

    @CommandHandler
    fun on(cmd: PlayerItemPickupCommand) {
        val uuid = cmd.player.uuid
        val filter = playerFilter[uuid] ?: return
        if (filter.contains(cmd.item)) cmd.cancel()
    }

    fun openFilterInventory(player: PlayerEntity) {
        val filter = getFilterOrCreate(player.uuid)

        val screenHandlerFactory = SimpleNamedScreenHandlerFactory({ syncId, inventory, _ ->
            ItemFilterScreenHandler(syncId, inventory, filter, this)
        }, Text.translatable("container.mcendgame.filter.title"))

        player.openHandledScreen(screenHandlerFactory)
    }

    private fun getFilterOrCreate(uuid: UUID): Set<Item> {
        if (!playerFilter.containsKey(uuid)) playerFilter[uuid] = setOf()
        return playerFilter[uuid]!!
    }

    fun saveItemFilter(player: PlayerEntity, inventory: Inventory) {
        val newFilter = mutableSetOf<Item>()

        for (i in 0 until inventory.size()) {
            val stack = inventory.getStack(i)
            if (stack.isEmpty) continue
            newFilter.add(stack.item)
        }

        playerFilter[player.uuid] = newFilter
    }
}