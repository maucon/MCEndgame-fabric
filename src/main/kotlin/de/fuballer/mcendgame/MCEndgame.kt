package de.fuballer.mcendgame

import de.fuballer.mcendgame.components.item_tag.ItemTagConfig
import de.maucon.mauconframework.MauConFramework
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.Configurator

object MCEndgame : ModInitializer {
    const val MOD_ID = "mcendgame"

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        MauConFramework.start(MCEndgame::class.java)
        ItemTagConfig
    }
}