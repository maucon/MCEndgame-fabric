package de.fuballer.mcendgame.client.component.render

import com.mojang.blaze3d.pipeline.BlendFunction
import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.gl.RenderPipelines
import net.minecraft.client.render.VertexFormats

object CustomRenderPipelines {
    val LINK_PIPELINE: RenderPipeline = RenderPipelines.register(
        RenderPipeline.builder()
            .withLocation("pipeline/link")
            .withVertexShader("core/position_color")
            .withFragmentShader("core/position_color")
            .withCull(false)
            .withBlend(BlendFunction.TRANSLUCENT)
            .withDepthWrite(true)
            .withVertexFormat(VertexFormats.POSITION_COLOR_LIGHT, VertexFormat.DrawMode.TRIANGLE_STRIP)
            .build()
    )
}