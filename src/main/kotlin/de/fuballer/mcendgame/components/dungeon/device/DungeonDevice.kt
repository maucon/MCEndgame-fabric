package de.fuballer.mcendgame.components.dungeon.device

import de.fuballer.mcendgame.components.dungeon.device.screen.DungeonDeviceScreenHandler
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Injectable
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
    val SCREEN_HANDLER = RegistryUtil.registerScreenHandler(NAME, ::DungeonDeviceScreenHandler)
}