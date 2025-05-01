package de.fuballer.mcendgame.component.block

import de.fuballer.mcendgame.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.block.AbstractBlock.Settings
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
}