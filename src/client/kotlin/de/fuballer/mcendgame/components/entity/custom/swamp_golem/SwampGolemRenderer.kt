package de.fuballer.mcendgame.components.entity.custom.swamp_golem

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer

class SwampGolemRenderer(
    context: EntityRendererFactory.Context,
) :
    MobEntityRenderer<SwampGolemEntity, SwampGolemRenderState, SwampGolemEntityModel>(
        context,
        SwampGolemEntityModel(context.getPart(SwampGolemEntityModel.SWAMP_GOLEM)),
        0.65F //shadow radius
    ) {

    override fun createRenderState(): SwampGolemRenderState =
        SwampGolemRenderState()

    override fun getTexture(state: SwampGolemRenderState) =
        IdentifierUtil.default("textures/entity/swamp_golem/swamp_golem.png")

    override fun updateRenderState(
        entity: SwampGolemEntity,
        renderState: SwampGolemRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(entity, renderState, tickDelta)
        renderState.slamAnimationState.copyFrom(entity.slamAnimationState)
        renderState.idleAnimationState.copyFrom(entity.idleAnimationState)
    }
}