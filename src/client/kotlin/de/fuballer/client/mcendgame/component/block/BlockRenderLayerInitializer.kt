package de.fuballer.client.mcendgame.component.block

import de.fuballer.mcendgame.components.block.CustomBlocks
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer

@Injectable
object BlockRenderLayerInitializer {
    @Initializer
    fun init() {
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.DECAYING_COBWEB, RenderLayer.getCutout())
    }
}