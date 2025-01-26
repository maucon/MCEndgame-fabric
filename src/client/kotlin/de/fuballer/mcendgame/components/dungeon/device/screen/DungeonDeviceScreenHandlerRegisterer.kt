package de.fuballer.mcendgame.components.dungeon.device.screen

import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.client.gui.screen.ingame.HandledScreens

@Injectable
object DungeonDeviceScreenHandlerRegisterer {
    @Initialize
    fun register(){
        val screenHandlerType = RegistryUtil.registerScreenHandler("dungeon_device", ::DungeonDeviceScreenHandler)
        HandledScreens.register(screenHandlerType, ::DungeonDeviceScreen)
    }
}