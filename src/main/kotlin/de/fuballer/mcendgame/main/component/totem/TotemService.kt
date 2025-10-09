package de.fuballer.mcendgame.main.component.totem

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getCustomAttributes
import de.fuballer.mcendgame.main.component.totem.db.PlayerTotemsEntity
import de.fuballer.mcendgame.main.component.totem.db.PlayerTotemsRepository
import de.fuballer.mcendgame.main.messaging.misc.CollectCustomAttributesCommand
import de.fuballer.mcendgame.main.util.extension.WorldExtension.isDungeonWorld
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.Text

@Injectable
class TotemService(
    private val playerTotemsRepository: PlayerTotemsRepository,
) {
    fun openInventory(player: PlayerEntity) {
        val playerTotems = getPlayerTotems(player)

        val screenHandlerFactory = SimpleNamedScreenHandlerFactory({ syncId, inventory, _ ->
            TotemScreenHandler(syncId, inventory, playerTotems, this)
        }, Text.translatable("container.mcendgame.totem.title"))

        player.openHandledScreen(screenHandlerFactory)
    }

    private fun getPlayerTotems(player: PlayerEntity) = playerTotemsRepository.findById(player.uuid)?.totems ?: listOf()

    fun savePlayerTotems(player: PlayerEntity, inventory: Inventory) {
        val entity = PlayerTotemsEntity(player.uuid, inventory.toList())
        playerTotemsRepository.save(entity)
    }

    @CommandHandler
    fun on(cmd: CollectCustomAttributesCommand) {
        val player = cmd.entity as? PlayerEntity ?: return
        if (!player.world.isDungeonWorld()) return

        val totems = getPlayerTotems(player)
        val attributes = totems.flatMap { it.getCustomAttributes() }
        cmd.customAttributes.addAll(attributes)
    }
}