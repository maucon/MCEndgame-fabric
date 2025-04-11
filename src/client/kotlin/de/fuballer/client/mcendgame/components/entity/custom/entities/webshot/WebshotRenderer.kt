package de.fuballer.client.mcendgame.components.entity.custom.entities.webshot

import de.fuballer.mcendgame.components.entity.custom.entities.webshot.WebshotEntity
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.RotationAxis

class WebshotRenderer(
    context: EntityRendererFactory.Context,
) : EntityRenderer<WebshotEntity, WebshotRenderState>(context) {
    val model =
        WebshotEntityModel(context.getPart(WebshotEntityModel.Companion.WEBSHOT))

    companion object {
        val TEXTURE = IdentifierUtil.default("textures/entity/webshot/webshot.png")
    }

    override fun render(
        webshotRenderState: WebshotRenderState,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        i: Int
    ) {
        matrixStack.push()
        matrixStack.translate(0.0f, 0.15f, 0.0f)
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(webshotRenderState.yaw + 180))
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(webshotRenderState.pitch))
        model.setAngles(webshotRenderState)

        val vertexConsumer = vertexConsumerProvider.getBuffer(model.getLayer(TEXTURE))
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV)
        matrixStack.pop()

        super.render(webshotRenderState, matrixStack, vertexConsumerProvider, i)
    }

    override fun createRenderState(): WebshotRenderState =
        WebshotRenderState()

    override fun updateRenderState(
        webshotEntity: WebshotEntity,
        webshotRenderState: WebshotRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(webshotEntity, webshotRenderState, tickDelta)
        webshotRenderState.pitch = webshotEntity.getLerpedPitch(tickDelta)
        webshotRenderState.yaw = webshotEntity.getLerpedYaw(tickDelta)
    }
}