package de.fuballer.mcendgame.client.component.entity.custom.entities.webshot

import de.fuballer.mcendgame.main.component.entity.custom.entities.webshot.WebshotEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.command.OrderedRenderCommandQueue
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.state.CameraRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.RotationAxis

class WebshotRenderer(
    context: EntityRendererFactory.Context,
) : EntityRenderer<WebshotEntity, WebshotRenderState>(context) {
    val model = WebshotEntityModel(context.getPart(WebshotEntityModel.WEBSHOT))

    companion object {
        val TEXTURE = IdentifierUtil.default("textures/entity/webshot/webshot.png")
    }

    override fun render(
        renderState: WebshotRenderState,
        matrices: MatrixStack,
        queue: OrderedRenderCommandQueue,
        cameraState: CameraRenderState
    ) {
        matrices.push()
        matrices.translate(0.0f, 0.15f, 0.0f)
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(renderState.yaw + 180))
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(renderState.pitch))
        model.setAngles(renderState)

        queue.submitModel(
            model,
            renderState,
            matrices,
            RenderLayers.entityCutout(TEXTURE),
            renderState.light,
            OverlayTexture.DEFAULT_UV,
            renderState.outlineColor,
            null,
        )
        matrices.pop()

        super.render(renderState, matrices, queue, cameraState)
    }

    override fun createRenderState(): WebshotRenderState = WebshotRenderState()

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