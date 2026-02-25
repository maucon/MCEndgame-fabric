package de.fuballer.mcendgame.client.component.render

import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.RenderSetup

object CustomRenderLayers {
    val LINK: RenderLayer = RenderLayer.of(
        "link",
        RenderSetup.builder(CustomRenderPipelines.LINK_PIPELINE)
            .useLightmap()
            .build()
    )
}