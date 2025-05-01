package de.fuballer.client.mcendgame.component.entity.custom.networking

import de.fuballer.mcendgame.components.entity.custom.interfaces.HookAttackMob
import de.fuballer.mcendgame.components.entity.custom.networking.EntityHookEntityPayload
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient

@Injectable
class EntityHookEntityPayloadReceiverRegisterer {
    @Initializer
    fun register() {
        ClientPlayNetworking.registerGlobalReceiver(EntityHookEntityPayload.ID) { payload, _ ->
            val world = MinecraftClient.getInstance().world ?: return@registerGlobalReceiver
            val hooker = world.getEntityById(payload.hookerId) ?: return@registerGlobalReceiver
            if (hooker !is HookAttackMob) return@registerGlobalReceiver

            if (payload.remove) {
                hooker.removeHookedEntity(payload.hookedId)
            } else {
                hooker.addHookedEntity(payload.hookedId)
            }
        }
    }
}