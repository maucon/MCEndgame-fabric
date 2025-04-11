package de.fuballer.mcendgame.components.entity.custom.networking

import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry

@Injectable
class EntityHookEntityPayloadRegisterer {
    @Initializer
    fun register() {
        PayloadTypeRegistry.playS2C().register(EntityHookEntityPayload.ID, EntityHookEntityPayload.CODEC)
    }
}