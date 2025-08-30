package de.fuballer.mcendgame.client.component.entity.custom.feature.ghostly

import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension.isGhostly
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity

class GhostlyFeatureRenderer<S : LivingEntityRenderState, M : EntityModel<S>>(
    val featureContext: FeatureRendererContext<S, M>,
) : FeatureRenderer<S, M>(featureContext) {
    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        state: S,
        limbAngle: Float,
        limbDistance: Float,
    ) {
        if (!state.isGhostly()) return

        val renderer = featureContext as LivingEntityRenderer<out LivingEntity, S, M>
        val texture = renderer.getTexture(state)
        val vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(texture))

        contextModel.render(
            matrices,
            vertexConsumer,
            light,
            OverlayTexture.DEFAULT_UV,
            0x8000FF00.toInt(),
        )
    }
}