package de.fuballer.mcendgame.client.component.entity.custom.feature.isolated

import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.client.util.math.MatrixStack


class IsolatedFeatureRenderer<T : LivingEntityRenderState, M : EntityModel<T>>(
    featureContext: FeatureRendererContext<T, M>,
) : FeatureRenderer<T, M>(featureContext) {

    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        state: T,
        limbAngle: Float,
        limbDistance: Float
    ) {
        IsolatedIndicatorRenderer.tryRender(state, matrices, vertexConsumers, light)
    }
}