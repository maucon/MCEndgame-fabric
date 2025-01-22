package de.fuballer.mcendgame

import de.fuballer.mcendgame.item.custom.armor.ArmorModelRegisterer
import de.maucon.mauconframework.MauConFramework
import net.fabricmc.api.ClientModInitializer

object MCEndgameClient : ClientModInitializer {
    override fun onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        println(MauConFramework.start(MCEndgameClient::class.java))

        ArmorModelRegisterer.register()
    }
}