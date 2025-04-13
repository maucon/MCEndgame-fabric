package de.fuballer.client.mcendgame.components.portal

import de.fuballer.mcendgame.components.portal.types.DefaultPortalEntity
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class DefaultPortalRenderer(
    context: EntityRendererFactory.Context,
) : LivingEntityRenderer<DefaultPortalEntity, DefaultPortalRenderState, DefaultPortalModel>(
    context,
    DefaultPortalModel(context.getPart(DefaultPortalModel.DEFAULT_PORTAL)),
    0.0F //shadow
) {
    override fun createRenderState(): DefaultPortalRenderState = DefaultPortalRenderState()

    override fun getTexture(state: DefaultPortalRenderState?): Identifier =
        IdentifierUtil.default("textures/entity/swamp_golem/swamp_golem.png")

    // do not render the name label
    override fun renderLabelIfPresent(state: DefaultPortalRenderState, text: Text, matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int) {}

    override fun updateRenderState(
        entity: DefaultPortalEntity,
        state: DefaultPortalRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(entity, state, tickDelta)
        state.startAnimationState.copyFrom(entity.startAnimationState)
        state.idleAnimationState.copyFrom(entity.idleAnimationState)
    }
}