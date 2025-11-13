package de.fuballer.mcendgame.client.component.render

import net.minecraft.client.render.RenderLayer

object CustomRenderLayers {
    val LINK: RenderLayer.MultiPhase = RenderLayer.of(
        "link",
        1536,
        CustomRenderPipelines.LINK_PIPELINE,
        RenderLayer.MultiPhaseParameters.builder()
            .texture(RenderLayer.NO_TEXTURE)
            .lightmap(RenderLayer.ENABLE_LIGHTMAP)
            .build(false)
    )
}