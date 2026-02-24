package de.fuballer.mcendgame.client.component.block

import de.fuballer.mcendgame.main.component.block.CustomBlocks
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap
import net.minecraft.client.render.BlockRenderLayer

@Injectable
object BlockRenderLayerInitializer {
    @Initializer
    fun init() {
        BlockRenderLayerMap.putBlock(CustomBlocks.DECAYING_COBWEB, BlockRenderLayer.CUTOUT)
        BlockRenderLayerMap.putBlock(CustomBlocks.CRYSTAL_FORGE, BlockRenderLayer.CUTOUT)
    }
}