package de.fuballer.mcendgame.components.block

import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer

@Injectable
object BlockRenderLayerInitializer {
    @Initialize
    fun init() {
        BlockRenderLayerMap.INSTANCE.putBlock(CustomBlocks.DECAYING_COBWEB, RenderLayer.getCutout())
    }
}