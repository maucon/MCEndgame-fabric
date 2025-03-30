package de.fuballer.mcendgame.components.entity.custom.entities.webshot

import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.ProjectileEntityRenderer

class WebshotRenderer(
    context: EntityRendererFactory.Context,
) : ProjectileEntityRenderer<WebshotEntity, WebshotRenderState>(
    context,
) {
    override fun getTexture(state: WebshotRenderState) =
        IdentifierUtil.default("textures/entity/webshot/webshot.png")

    override fun createRenderState(): WebshotRenderState = WebshotRenderState()

    override fun updateRenderState(
        webshotEntity: WebshotEntity,
        webshotenderState: WebshotRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(webshotEntity, webshotenderState, tickDelta)
    }
}