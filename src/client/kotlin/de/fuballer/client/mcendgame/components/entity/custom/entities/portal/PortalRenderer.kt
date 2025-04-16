package de.fuballer.client.mcendgame.components.entity.custom.entities.portal

import de.fuballer.client.mcendgame.components.entity.custom.entities.portal.type.PortalRenderType
import de.fuballer.mcendgame.components.portal.PortalEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class PortalRenderer(
    private val context: EntityRendererFactory.Context,
) : LivingEntityRenderer<PortalEntity, PortalRenderState, EntityModel<PortalRenderState>>(
    context,
    null,
    0.0F
) {
    override fun createRenderState(): PortalRenderState = PortalRenderState()

    // do not render the name label
    override fun renderLabelIfPresent(state: PortalRenderState, text: Text, matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int) {}

    override fun getTexture(state: PortalRenderState): Identifier = state.type.getTexture()

    override fun getShadowRadius(state: PortalRenderState): Float {
        return state.type.getShadowRadius()
    }

    override fun updateRenderState(
        entity: PortalEntity,
        state: PortalRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(entity, state, tickDelta)
        val typeId = entity.dataTracker.get(PortalEntity.TYPE)
        state.type = PortalRenderType.getType(typeId)

        state.openAnimationState.copyFrom(entity.type.openAnimationState)
        state.idleAnimationState.copyFrom(entity.type.idleAnimationState)
        state.closeAnimationState.copyFrom(entity.type.closeAnimationState)
    }

    override fun render(state: PortalRenderState, matrixStack: MatrixStack, vertexConsumerProvider: VertexConsumerProvider, i: Int) {
        this.model = state.type.getModel(context)
        super.render(state, matrixStack, vertexConsumerProvider, i)
    }
}