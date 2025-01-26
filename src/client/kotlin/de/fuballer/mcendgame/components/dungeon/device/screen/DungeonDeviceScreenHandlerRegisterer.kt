package de.fuballer.mcendgame.components.dungeon.device.screen

import de.fuballer.mcendgame.components.dungeon.device.DungeonDevice
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.client.gui.screen.ingame.HandledScreens

@Injectable
object DungeonDeviceScreenHandlerRegisterer {
    @Initialize
    fun register() {
        HandledScreens.register(DungeonDevice.SCREEN_HANDLER, ::DungeonDeviceScreen)
    }
}