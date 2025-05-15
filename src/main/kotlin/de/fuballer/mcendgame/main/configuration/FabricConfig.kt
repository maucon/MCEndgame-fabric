package de.fuballer.mcendgame.main.configuration

import de.maucon.mauconframework.di.annotation.Configuration
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.loader.api.FabricLoader

@Configuration
object FabricConfig {
    @Injectable
    fun fabricLoader(): FabricLoader = FabricLoader.getInstance()
}