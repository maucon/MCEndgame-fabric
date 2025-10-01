package de.fuballer.mcendgame.main.component.totem

import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.Text

@Injectable
class TotemService {
    fun openInventory(player: PlayerEntity) {
        val screenHandlerFactory = SimpleNamedScreenHandlerFactory({ syncId, inventory, _ ->
            TotemScreenHandler(syncId, inventory)
        }, Text.translatable("container.mcendgame.totem.title"))

        player.openHandledScreen(screenHandlerFactory)
    }
}