package de.fuballer.mcendgame.component.dungeon.device

import de.fuballer.mcendgame.component.block.CustomBlocks
import de.fuballer.mcendgame.component.dungeon.device.networking.OpenDungeonPayload
import de.fuballer.mcendgame.component.dungeon.device.screen.DungeonDeviceScreenHandler
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType

@Injectable
object DungeonDevice {
    const val NAME = "dungeon_device"

    val BLOCK = CustomBlocks.DUNGEON_DEVICE
    val BLOCK_ENTITY_TYPE = RegistryUtil.registerBlockEntityType(::DungeonDeviceBlockEntity, BLOCK, NAME)
    val EXTENDED_SCREEN_HANDLER = ExtendedScreenHandlerType(
        { syncId, inventory, payload -> DungeonDeviceScreenHandler(syncId, inventory, payload = payload) },
        OpenDungeonPayload.CODEC
    ).also { RegistryUtil.registerScreenHandler(NAME, it) }
}