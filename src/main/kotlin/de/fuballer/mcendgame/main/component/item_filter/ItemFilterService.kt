package de.fuballer.mcendgame.main.component.item_filter

import de.fuballer.mcendgame.main.component.item_filter.db.ItemFilterEntity
import de.fuballer.mcendgame.main.component.item_filter.db.ItemFilterRepository
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.Item
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.Text
import java.util.*

@Injectable
class ItemFilterService(
    private val itemFilterRepo: ItemFilterRepository,
) {
    @CommandHandler
    fun on(cmd: PlayerItemPickupCommand) {
        val player = cmd.player
        if (!player.entityWorld.isDungeonWorld()) return

        val uuid = player.uuid
        val filter = itemFilterRepo.findById(uuid)?.items ?: return
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
        val entity = itemFilterRepo.findById(uuid)
            ?: return setOf()

        return entity.items
    }

    fun saveItemFilter(player: PlayerEntity, inventory: Inventory) {
        val newFilter = mutableSetOf<Item>()

        for (i in 0 until inventory.size()) {
            val stack = inventory.getStack(i)
            if (stack.isEmpty) continue

            newFilter.add(stack.item)
        }

        val entity = ItemFilterEntity(player.uuid, newFilter)
        itemFilterRepo.save(entity)
    }
}