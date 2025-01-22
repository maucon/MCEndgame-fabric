package de.fuballer.mcendgame

import de.maucon.mauconframework.MauConFramework
import de.fuballer.mcendgame.item.custom.armor.CustomArmorItems
import net.fabricmc.api.ModInitializer

object MCEndgame : ModInitializer {
    const val MOD_ID = "mcendgame"

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        MauConFramework.start(MCEndgame::class.java)

        CustomArmorItems
    }
}