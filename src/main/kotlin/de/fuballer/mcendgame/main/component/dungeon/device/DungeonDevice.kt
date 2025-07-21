package de.fuballer.mcendgame.main.component.dungeon.device

import de.fuballer.mcendgame.main.component.dungeon.device.networking.DungeonDevicePayload
import de.fuballer.mcendgame.main.component.dungeon.device.screen.DungeonDeviceScreenHandler
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Blocks

@Injectable
object DungeonDevice {
    private const val NAME = "dungeon_device"

    val BLOCK = RegistryUtil.registerBlock(
        ::DungeonDeviceBlock,
        Settings.create()
            .resistance(1200F)
            .hardness(10F)
            .requiresTool(),
        NAME
    )
    val BLOCK_ENTITY_TYPE = RegistryUtil.registerBlockEntityType(::DungeonDeviceBlockEntity, BLOCK, NAME)

    val EXTENDED_SCREEN_HANDLER = ExtendedScreenHandlerType(
        { syncId, inventory, payload -> DungeonDeviceScreenHandler(syncId, inventory, payload = payload) },
        DungeonDevicePayload.CODEC
    ).also { RegistryUtil.registerScreenHandler(NAME, it) }
}