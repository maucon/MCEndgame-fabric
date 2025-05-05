package de.fuballer.mcendgame.client.component.entity.custom.entities.webhook

import de.fuballer.mcendgame.main.component.entity.custom.entities.webhook.WebhookEntity
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory

class WebhookRenderer(
    context: EntityRendererFactory.Context,
) : EntityRenderer<WebhookEntity, WebhookRenderState>(context) {
    override fun createRenderState(): WebhookRenderState = WebhookRenderState()
}