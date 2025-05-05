package de.fuballer.mcendgame.client

import de.maucon.mauconframework.MauConFramework
import net.fabricmc.api.ClientModInitializer

object MCEndgameClient : ClientModInitializer {
    override fun onInitializeClient() {
        MauConFramework.start(MCEndgameClient::class.java)
    }
}