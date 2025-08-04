package de.fuballer.mcendgame.client.component.screen

import de.fuballer.mcendgame.main.component.dungeon.device.DungeonDevice
import de.fuballer.mcendgame.main.component.killer.KillerService
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.minecraft.client.gui.screen.ingame.HandledScreens

@Injectable
object ScreenRegisterer {
    @Initializer
    fun register() {
        HandledScreens.register(DungeonDevice.EXTENDED_SCREEN_HANDLER, ::DungeonDeviceScreen)
        HandledScreens.register(KillerService.SCREEN_HANDLER_TYPE, ::KillerScreen)
    }
}