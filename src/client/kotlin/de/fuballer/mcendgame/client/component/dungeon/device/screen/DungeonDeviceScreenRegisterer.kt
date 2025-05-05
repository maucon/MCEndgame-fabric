package de.fuballer.mcendgame.client.component.dungeon.device.screen

import de.fuballer.mcendgame.component.dungeon.device.DungeonDevice
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.minecraft.client.gui.screen.ingame.HandledScreens

@Injectable
object DungeonDeviceScreenRegisterer {
    @Initializer
    fun register() {
        HandledScreens.register(DungeonDevice.EXTENDED_SCREEN_HANDLER, ::DungeonDeviceScreen)
    }
}