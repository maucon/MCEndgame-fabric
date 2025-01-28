package de.fuballer.mcendgame.components.dungeon.device.networking

import de.fuballer.mcendgame.configuration.RuntimeConfig
import de.fuballer.mcendgame.event.DungeonOpenEvent
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking

@Injectable
class OpenDungeonPayloadRegisterer {
    @Initialize
    fun register() {
        PayloadTypeRegistry.playC2S().register(OpenDungeonPayload.ID, OpenDungeonPayload.CODEC)

        ServerPlayNetworking.registerGlobalReceiver(OpenDungeonPayload.ID) { openDungeonPayload, _ ->
            val blockEntity = RuntimeConfig.SERVER.getWorld(openDungeonPayload.worldKey)?.getBlockEntity(openDungeonPayload.pos) ?: return@registerGlobalReceiver
            val playerEntity = RuntimeConfig.SERVER.playerManager.getPlayer(openDungeonPayload.playerId) ?: return@registerGlobalReceiver

            DungeonOpenEvent.NOTIFIER.interact(DungeonOpenEvent(blockEntity, playerEntity))
        }
    }
}