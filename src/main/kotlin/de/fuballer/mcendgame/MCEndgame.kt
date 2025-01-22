package de.fuballer.mcendgame

import de.maucon.mauconframework.MauConFramework
import net.fabricmc.api.ModInitializer
import net.minecraft.server.MinecraftServer

object MCEndgame : ModInitializer {
    const val MOD_ID = "mcendgame"

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        MauConFramework.start(MCEndgame::class.java)
    }
}