package de.fuballer.mcendgame.components.dungeon.device.networking

import de.fuballer.mcendgame.configuration.RuntimeConfig
import de.fuballer.mcendgame.event.DungeonOpenEvent
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking

@Injectable
class OpenDungeonPayloadRegisterer {
    @Initializer
    fun register() {
        PayloadTypeRegistry.playC2S().register(OpenDungeonPayload.ID, OpenDungeonPayload.CODEC)

        ServerPlayNetworking.registerGlobalReceiver(OpenDungeonPayload.ID) { openDungeonPayload, _ ->
            val blockEntity = RuntimeConfig.SERVER.getWorld(openDungeonPayload.worldKey)?.getBlockEntity(openDungeonPayload.pos) ?: return@registerGlobalReceiver
            val playerEntity = RuntimeConfig.SERVER.playerManager.getPlayer(openDungeonPayload.playerId) ?: return@registerGlobalReceiver

            DungeonOpenEvent.NOTIFIER.interact(DungeonOpenEvent(blockEntity, playerEntity))
        }
    }
}