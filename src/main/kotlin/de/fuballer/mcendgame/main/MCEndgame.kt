package de.fuballer.mcendgame.main

import de.maucon.mauconframework.MauConFramework
import net.fabricmc.api.ModInitializer
import java.util.*

object MCEndgame : ModInitializer {
    const val MOD_ID = "mcendgame"

    override fun onInitialize() {
        Locale.setDefault(Locale.US)
        MauConFramework.start(MCEndgame::class.java)
    }
}