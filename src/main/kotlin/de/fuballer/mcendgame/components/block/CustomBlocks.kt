package de.fuballer.mcendgame.components.block

import de.fuballer.mcendgame.components.dungeon.device.DungeonDevice
import de.fuballer.mcendgame.components.dungeon.device.DungeonDeviceBlock
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Blocks
import net.minecraft.block.MapColor
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.sound.BlockSoundGroup

@Injectable
object CustomBlocks {
    val DECAYING_COBWEB = RegistryUtil.registerBlock(
        ::DecayingCobwebBlock,
        Settings.create()
            .mapColor(MapColor.WHITE_GRAY)
            .sounds(BlockSoundGroup.COBWEB)
            .solid()
            .noCollision()
            .requiresTool()
            .dropsNothing()
            .strength(4.0f)
            .pistonBehavior(PistonBehavior.DESTROY),
        DecayingCobwebBlock.NAME
    )

    val DUNGEON_DEVICE = RegistryUtil.registerBlock(
        ::DungeonDeviceBlock,
        Settings.create()
            .resistance(Blocks.BEDROCK.blastResistance),
        DungeonDevice.NAME
    )
}