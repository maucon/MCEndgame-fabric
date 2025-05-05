package de.fuballer.mcendgame.main.component.dungeon.device

import de.fuballer.mcendgame.main.component.dungeon.device.networking.OpenDungeonPayload
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
            .resistance(Blocks.BEDROCK.blastResistance),
        NAME
    )
    val BLOCK_ENTITY_TYPE = RegistryUtil.registerBlockEntityType(::DungeonDeviceBlockEntity, BLOCK, NAME)

    val EXTENDED_SCREEN_HANDLER = ExtendedScreenHandlerType(
        { syncId, inventory, payload -> DungeonDeviceScreenHandler(syncId, inventory, payload = payload) },
        OpenDungeonPayload.CODEC
    ).also { RegistryUtil.registerScreenHandler(NAME, it) }
}