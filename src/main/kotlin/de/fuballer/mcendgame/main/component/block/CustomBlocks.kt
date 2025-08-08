package de.fuballer.mcendgame.main.component.block

import de.fuballer.mcendgame.main.component.block.crystalforge.CrystalForgeBlock
import de.fuballer.mcendgame.main.component.block.dungeon_device.DungeonDeviceBlock
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.MapColor
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.sound.BlockSoundGroup

@Injectable
object CustomBlocks {
    val DUNGEON_DEVICE = RegistryUtil.registerBlock(
        ::DungeonDeviceBlock,
        Settings.create()
            .resistance(1200F)
            .hardness(10F)
            .requiresTool(),
        DungeonDeviceBlock.ID,
    )

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
        DecayingCobwebBlock.ID,
    )

    val CRYSTAL_FORGE = RegistryUtil.registerBlock(
        ::CrystalForgeBlock,
        Settings.create()
            .resistance(1200F)
            .hardness(10F)
            .requiresTool(),
        CrystalForgeBlock.ID,
    )
}