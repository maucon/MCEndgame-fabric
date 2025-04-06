package de.fuballer.mcendgame.components.entity.custom.networking

import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry

@Injectable
class EntityHookEntityPayloadRegisterer {
    @Initialize
    fun register() {
        PayloadTypeRegistry.playS2C().register(EntityHookEntityPayload.ID, EntityHookEntityPayload.CODEC)
    }
}