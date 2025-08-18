package de.fuballer.mcendgame.main.component.killer

import de.fuballer.mcendgame.main.component.killer.db.KillerEntity
import de.fuballer.mcendgame.main.component.killer.db.KillerRepository
import de.fuballer.mcendgame.main.component.killer.networking.KillerEntityPayload
import de.fuballer.mcendgame.main.messaging.misc.PlayerEntityDeathEvent
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Injectable
class KillerService(
    private val killerRepo: KillerRepository,
) {
    companion object {
        val SCREEN_HANDLER_TYPE = ExtendedScreenHandlerType(
            { syncId, inventory, payload -> KillerScreenHandler(syncId, inventory, payload = payload) },
            KillerEntityPayload.CODEC
        ).also { RegistryUtil.registerScreenHandler("killer", it) }
    }

    fun openKillerInventory(
        commandExecutor: PlayerEntity,
        killedPlayerUUID: UUID,
    ): Boolean {
        val killerEntity = killerRepo.findById(killedPlayerUUID) ?: return false
        val killerEntityPayload = KillerEntityPayload(killerEntity)
        val killerName = killerEntity.displayName.getOrNull() ?: Text.translatable("entity.mcendgame.unknown")

        val screenHandlerFactory = KillerScreenHandlerFactory(killerEntityPayload, killerName)
        { syncId, playerInventory, _ -> KillerScreenHandler(syncId, playerInventory, killerEntityPayload) }

        commandExecutor.openHandledScreen(screenHandlerFactory)

        return true
    }

    @EventSubscriber
    fun on(event: PlayerEntityDeathEvent) {
        val player = event.player
        if (player.world.isClient) return
        val killer = event.killer ?: return

        killerRepo.save(KillerEntity.of(player, killer))
    }
}