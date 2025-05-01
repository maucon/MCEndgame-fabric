package de.fuballer.client.mcendgame.component.item.custom.armor

import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.state.EntityRenderState

interface CustomVertexConsumer {
    fun getVertexConsumer(
        renderState: EntityRenderState,
        provider: VertexConsumerProvider,
        default: VertexConsumer,
    ): VertexConsumer
}