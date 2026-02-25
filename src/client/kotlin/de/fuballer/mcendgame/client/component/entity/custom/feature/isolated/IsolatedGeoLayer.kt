package de.fuballer.mcendgame.client.component.entity.custom.feature.isolated

import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.cache.model.BakedGeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState
import software.bernie.geckolib.renderer.base.GeoRenderer
import software.bernie.geckolib.renderer.layer.GeoRenderLayer

class IsolatedGeoLayer<T : GeoAnimatable, O : Any, R : GeoRenderState>(
    renderer: GeoRenderer<T, O, R>,
) : GeoRenderLayer<T, O, R>(renderer) {

    override fun render(
        renderState: R,
        poseStack: MatrixStack,
        bakedModel: BakedGeoModel,
        renderType: RenderLayer?,
        bufferSource: VertexConsumerProvider,
        buffer: VertexConsumer?,
        packedLight: Int,
        packedOverlay: Int,
        renderColor: Int
    ) {
        val livingEntityRenderState = renderState as? LivingEntityRenderState ?: return
        IsolatedIndicatorRenderer.tryRender(livingEntityRenderState, poseStack, bufferSource, packedLight, true)
    }
}