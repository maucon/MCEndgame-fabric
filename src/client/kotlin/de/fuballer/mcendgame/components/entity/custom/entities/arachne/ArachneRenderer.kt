package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier

class ArachneRenderer(
    context: EntityRendererFactory.Context,
) : MobEntityRenderer<ArachneEntity, ArachneRenderState, ArachneEntityModel>(
    context,
    ArachneEntityModel(context.getPart(ArachneEntityModel.ARACHNE)),
    0.8F //shadow
) {
    override fun createRenderState(): ArachneRenderState =
        ArachneRenderState()

    override fun getTexture(state: ArachneRenderState): Identifier {
        if (state.isSaddled) return IdentifierUtil.default("textures/entity/arachne/arachne_saddled.png")
        return IdentifierUtil.default("textures/entity/arachne/arachne.png")
    }

    override fun updateRenderState(
        entity: ArachneEntity,
        renderState: ArachneRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(entity, renderState, tickDelta)

        renderState.isSaddled = entity.isSaddled
        renderState.walkAnimationState.copyFrom(entity.walkAnimationState)
        renderState.moveSpeed = entity.getAttributeValue(EntityAttributes.MOVEMENT_SPEED).toFloat()
    }
}