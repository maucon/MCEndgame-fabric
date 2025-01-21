package de.fuballer.mcendgame

import de.fuballer.mcendgame.item.custom.armor.CustomArmorItems
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object MCEndgame : ModInitializer {
    val MOD_ID = "mcendgame"
    private val logger = LoggerFactory.getLogger(MCEndgame::class.java)

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world!")

        CustomArmorItems
    }
}