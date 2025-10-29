package de.fuballer.mcendgame.client.messaging

import de.fuballer.mcendgame.client.component.entity.custom.data.MultipleEntityConnectionData
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.client.util.math.MatrixStack

data class RenderLinksCommand(
    val renderState: LivingEntityRenderState,
    val matrixStack: MatrixStack,
    val vertexConsumerProvider: VertexConsumerProvider,
    val data: MultipleEntityConnectionData,
)