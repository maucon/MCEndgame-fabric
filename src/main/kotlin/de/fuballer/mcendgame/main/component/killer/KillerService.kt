package de.fuballer.mcendgame.main.component.killer

import de.fuballer.mcendgame.main.component.killer.db.KillerEntity
import de.fuballer.mcendgame.main.component.killer.db.KillerRepository
import de.fuballer.mcendgame.main.component.killer.networking.KillerEntityPayload
import de.fuballer.mcendgame.main.messaging.misc.PlayerEntityDeathEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.player.PlayerEntity

@Injectable
class KillerService(
    private val killerRepo: KillerRepository,
) {
    fun openKillerInventory(
        commandExecutor: PlayerEntity,
        killedPlayer: PlayerEntity,
    ): Boolean {
        val killerEntity = killerRepo.findById(killedPlayer.uuid) ?: return false
        val killerEntityPayload = KillerEntityPayload(killerEntity)
        val killerName = killerEntity.getNameOrTypeName()

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