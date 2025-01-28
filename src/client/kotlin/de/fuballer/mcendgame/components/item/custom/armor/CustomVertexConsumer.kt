package de.fuballer.mcendgame.components.item.custom.armor

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