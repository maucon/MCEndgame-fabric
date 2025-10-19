package de.fuballer.mcendgame.main.component.block

import de.fuballer.mcendgame.main.component.block.dungeon_device.DungeonDeviceBlock
import de.fuballer.mcendgame.main.component.block.dungeon_device.DungeonDeviceBlockEntity
import de.fuballer.mcendgame.main.component.block.totem_statue.TotemStatueBlock
import de.fuballer.mcendgame.main.component.block.totem_statue.TotemStatueBlockEntity
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.block.entity.BlockEntityType

@Injectable
object CustomBlockEntityTypes {
    val DUNGEON_DEVICE = RegistryUtil.registerBlockEntityType(::DungeonDeviceBlockEntity, CustomBlocks.DUNGEON_DEVICE, DungeonDeviceBlock.ID)
    val TOTEM_STATUE = RegistryUtil.registerBlockEntityType(::TotemStatueBlockEntity, CustomBlocks.TOTEM_STATUE, TotemStatueBlock.ID)
}