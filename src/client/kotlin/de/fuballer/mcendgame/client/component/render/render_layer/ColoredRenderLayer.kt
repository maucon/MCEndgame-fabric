package de.fuballer.mcendgame.client.component.render.render_layer

import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState
import software.bernie.geckolib.renderer.base.GeoRenderer
import software.bernie.geckolib.renderer.layer.TextureLayerGeoLayer

class ColoredRenderLayer<T : GeoAnimatable, O, R : GeoRenderState>(
    renderer: GeoRenderer<T, O, R>,
    texture: Identifier,
    private val color: Int,
) : TextureLayerGeoLayer<T, O, R>(renderer, texture) {
    override fun render(
        renderState: R,
        poseStack: MatrixStack,
        bakedModel: BakedGeoModel,
        renderType: RenderLayer?,
        bufferSource: VertexConsumerProvider,
        buffer: VertexConsumer?,
        packedLight: Int,
        packedOverlay: Int,
        renderColor: Int,
    ) {
        super.render(renderState, poseStack, bakedModel, renderType, bufferSource, buffer, packedLight, packedOverlay, color)
    }
}