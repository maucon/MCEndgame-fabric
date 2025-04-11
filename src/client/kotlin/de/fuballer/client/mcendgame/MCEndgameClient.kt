package de.fuballer.client.mcendgame

import de.maucon.mauconframework.MauConFramework
import net.fabricmc.api.ClientModInitializer

object MCEndgameClient : ClientModInitializer {
    override fun onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        MauConFramework.start(MCEndgameClient::class.java)
    }
}