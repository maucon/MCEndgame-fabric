package de.fuballer.mcendgame.components.dungeon.device.screen

import de.fuballer.mcendgame.components.dungeon.device.DungeonDevice
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.client.gui.screen.ingame.HandledScreens

@Injectable
object DungeonDeviceScreenRegisterer {
    @Initialize
    fun register() {
        HandledScreens.register(DungeonDevice.EXTENDED_SCREEN_HANDLER, ::DungeonDeviceScreen)
    }
}