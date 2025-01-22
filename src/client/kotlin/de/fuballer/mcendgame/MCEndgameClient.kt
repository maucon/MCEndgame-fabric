package de.fuballer.mcendgame

import de.fuballer.mcendgame.item.custom.armor.helmet.iceborne.IceborneModel
import de.maucon.mauconframework.MauConFramework
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry


object MCEndgameClient : ClientModInitializer {
    override fun onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        println(MauConFramework.start(MCEndgameClient::class.java))

        EntityModelLayerRegistry.registerModelLayer(
            IceborneModel.MODEL_LAYER,
            IceborneModel.Companion::getTexturedModelData
        )
    }
}