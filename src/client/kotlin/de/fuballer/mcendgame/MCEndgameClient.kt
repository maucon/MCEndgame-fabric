package de.fuballer.mcendgame

import de.maucon.mauconframework.MauConFramework
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.world.World

object MCEndgameClient : ClientModInitializer {
    override fun onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        MauConFramework.start(MCEndgameClient::class.java)
    }
}