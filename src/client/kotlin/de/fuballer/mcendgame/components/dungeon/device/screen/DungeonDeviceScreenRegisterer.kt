package de.fuballer.mcendgame.components.dungeon.device.screen

import de.fuballer.mcendgame.components.dungeon.device.DungeonDevice
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.client.gui.screen.ingame.HandledScreens

@Injectable
object DungeonDeviceScreenRegisterer {
    @Initializer
    fun register() {
        HandledScreens.register(DungeonDevice.EXTENDED_SCREEN_HANDLER, ::DungeonDeviceScreen)
    }
}