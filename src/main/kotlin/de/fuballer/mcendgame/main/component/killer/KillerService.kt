package de.fuballer.mcendgame.main.component.killer

import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.resource.featuretoggle.FeatureFlags
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.Text

@Injectable
class KillerService {
    fun openKillerInventory(player: PlayerEntity) {
        val screenHandlerFactory = SimpleNamedScreenHandlerFactory({ syncId, _, _ ->
            KillerScreenHandler(syncId, player.inventory)
        }, Text.translatable("container.mcendgame.killer.title"))

        player.openHandledScreen(screenHandlerFactory)
    }

    companion object {
        val SCREEN_HANDLER_TYPE = ScreenHandlerType<KillerScreenHandler>(::KillerScreenHandler, FeatureFlags.VANILLA_FEATURES)
            .also { RegistryUtil.registerScreenHandler("killer", it) }
    }
}