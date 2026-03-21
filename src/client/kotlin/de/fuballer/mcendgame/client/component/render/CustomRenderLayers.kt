package de.fuballer.mcendgame.client.component.render

import net.minecraft.client.render.OutputTarget
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.RenderSetup
import net.minecraft.util.Identifier
import net.minecraft.util.Util
import java.util.function.Function

object CustomRenderLayers {
    val LINK: RenderLayer = RenderLayer.of(
        "link",
        RenderSetup.builder(CustomRenderPipelines.LINK_PIPELINE)
            .useLightmap()
            .build()
    )

    fun ghostly(texture: Identifier) = GHOSTLY.apply(texture)
    val GHOSTLY: Function<Identifier, RenderLayer> = Util.memoize<Identifier, RenderLayer> { texture ->
        val renderSetup = RenderSetup.builder(CustomRenderPipelines.GHOSTLY_PIPELINE)
            .texture("Sampler0", texture)
            .outputTarget(OutputTarget.MAIN_TARGET)
            .useLightmap()
            .useOverlay()
            .translucent()
            .outlineMode(RenderSetup.OutlineMode.AFFECTS_OUTLINE)
            .build()
        RenderLayer.of("ghostly", renderSetup)
    }
}