package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer

class ArachneRenderer(
    context: EntityRendererFactory.Context,
) : MobEntityRenderer<ArachneEntity, ArachneRenderState, ArachneEntityModel>(
    context,
    ArachneEntityModel(context.getPart(ArachneEntityModel.ARACHNE)),
    0.8F //shadow
) {
    override fun createRenderState(): ArachneRenderState =
        ArachneRenderState()

    override fun getTexture(state: ArachneRenderState) =
        IdentifierUtil.default("textures/entity/arachne/arachne.png")

    override fun updateRenderState(
        entity: ArachneEntity,
        renderState: ArachneRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(entity, renderState, tickDelta)

        val movingStart = entity.dataTracker.get(ArachneEntity.MOVING_START)
        val movingTicks = entity.world.time - movingStart
        renderState.isMoving = movingStart >= 0
        renderState.movingTicks = movingTicks.toFloat() + tickDelta
    }
}