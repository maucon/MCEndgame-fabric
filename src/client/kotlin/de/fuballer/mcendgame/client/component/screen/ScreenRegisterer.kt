package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.screen.CustomScreenHandlerTypes
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.minecraft.client.gui.screen.ingame.HandledScreens

@Injectable
object ScreenRegisterer {
    @Initializer
    fun register() {
        HandledScreens.register(CustomScreenHandlerTypes.DUNGEON_DEVICE, ::DungeonDeviceScreen)
        HandledScreens.register(CustomScreenHandlerTypes.KILLER, ::KillerScreen)
        HandledScreens.register(CustomScreenHandlerTypes.CRYSTAL_FORGE, ::CrystalForgeScreen)
        HandledScreens.register(CustomScreenHandlerTypes.TOTEM, ::TotemScreen)
    }
}