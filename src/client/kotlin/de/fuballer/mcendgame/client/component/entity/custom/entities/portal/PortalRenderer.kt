package de.fuballer.mcendgame.client.component.entity.custom.entities.portal

import de.fuballer.mcendgame.client.component.entity.custom.entities.portal.type.PortalRenderType
import de.fuballer.mcendgame.component.portal.PortalEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class PortalRenderer(
    private val context: EntityRendererFactory.Context,
) : LivingEntityRenderer<PortalEntity, de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState, EntityModel<de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState>>(
    context,
    null,
    0.0F
) {
    override fun createRenderState(): de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState =
        de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState()

    // do not render the name label
    override fun renderLabelIfPresent(state: de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState, text: Text, matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int) {}

    override fun getTexture(state: de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState): Identifier = state.type.getTexture(state.age)

    override fun getRenderLayer(state: de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState, showBody: Boolean, translucent: Boolean, showOutline: Boolean): RenderLayer? {
        return state.type.getRenderLayer(this, state, showBody, translucent, showOutline)
            ?: super.getRenderLayer(state, showBody, translucent, showOutline)
    }

    override fun getShadowRadius(state: de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState): Float {
        return state.type.getShadowRadius()
    }

    override fun updateRenderState(
        entity: PortalEntity,
        state: de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(entity, state, tickDelta)
        val typeId = entity.dataTracker.get(PortalEntity.TYPE)
        state.type = PortalRenderType.getType(typeId)

        state.openAnimationState.copyFrom(entity.type.openAnimationState)
        state.idleAnimationState.copyFrom(entity.type.idleAnimationState)
        state.closeAnimationState.copyFrom(entity.type.closeAnimationState)
    }

    override fun render(state: de.fuballer.mcendgame.client.component.entity.custom.entities.portal.PortalRenderState, matrixStack: MatrixStack, vertexConsumerProvider: VertexConsumerProvider, i: Int) {
        this.model = state.type.getModel(context)
        super.render(state, matrixStack, vertexConsumerProvider, i)
    }
}