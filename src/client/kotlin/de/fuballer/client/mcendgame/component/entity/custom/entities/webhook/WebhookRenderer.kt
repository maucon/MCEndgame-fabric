package de.fuballer.client.mcendgame.component.entity.custom.entities.webhook

import de.fuballer.mcendgame.components.entity.custom.entities.webhook.WebhookEntity
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory

class WebhookRenderer(
    context: EntityRendererFactory.Context,
) : EntityRenderer<WebhookEntity, WebhookRenderState>(context) {
    override fun createRenderState(): WebhookRenderState = WebhookRenderState()
}