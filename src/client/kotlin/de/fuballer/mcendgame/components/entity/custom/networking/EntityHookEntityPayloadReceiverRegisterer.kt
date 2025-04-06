package de.fuballer.mcendgame.components.entity.custom.networking

import de.fuballer.mcendgame.components.entity.custom.interfaces.HookAttackMob
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient

@Injectable
class EntityHookEntityPayloadReceiverRegisterer {
    @Initialize
    fun register() {
        ClientPlayNetworking.registerGlobalReceiver(EntityHookEntityPayload.ID) { payload, _ ->
            val world = MinecraftClient.getInstance().world ?: return@registerGlobalReceiver
            val hooker = world.getEntityById(payload.hookerId) ?: return@registerGlobalReceiver
            val hooked = world.getEntityById(payload.hookedId) ?: return@registerGlobalReceiver

            if (hooker !is HookAttackMob) return@registerGlobalReceiver
            hooker.addHookedEntity(hooked)
        }
    }
}