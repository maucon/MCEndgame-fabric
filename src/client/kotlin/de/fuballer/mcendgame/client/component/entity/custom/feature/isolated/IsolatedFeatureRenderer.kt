package de.fuballer.mcendgame.client.component.entity.custom.feature.isolated

import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension.isIsolated
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributeUtil.canSeeIsolated
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.RotationAxis

private val ICON_TEXTURE = IdentifierUtil.default("textures/indicator/isolated.png")

class IsolatedFeatureRenderer<T : LivingEntityRenderState, M : EntityModel<T>>(
    featureContext: FeatureRendererContext<T, M>,
) : FeatureRenderer<T, M>(featureContext) {

    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        state: T,
        limbAngle: Float,
        limbDistance: Float
    ) {
        if (state.deathTime > 0) return

        if (!state.isIsolated()) return
        val player = MinecraftClient.getInstance().player ?: return
        if (!player.canSeeIsolated()) return

        renderIcon(matrices, vertexConsumers, light, state)
    }

    private fun renderIcon(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        state: T,
    ) {
        matrices.push()

        matrices.translate(0.0, 1.5 - state.entityType.height - 0.5, 0.0) // 1.5 moves translate to feet

        val camera = MinecraftClient.getInstance().gameRenderer.camera
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(state.bodyYaw - camera.yaw))
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.pitch))

        val buffer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(ICON_TEXTURE))
        val matrix = matrices.peek().positionMatrix

        buffer.vertex(matrix, -0.2f, -0.2f, 0f)
            .color(255, 255, 255, 255)
            .texture(0f, 0f)
            .overlay(OverlayTexture.DEFAULT_UV)
            .light(light)
            .normal(0f, 0f, 1f)

        buffer.vertex(matrix, 0.2f, -0.2f, 0f)
            .color(255, 255, 255, 255)
            .texture(1f, 0f)
            .overlay(OverlayTexture.DEFAULT_UV)
            .light(light)
            .normal(0f, 0f, 1f)

        buffer.vertex(matrix, 0.2f, 0.2f, 0f)
            .color(255, 255, 255, 255)
            .texture(1f, 1f)
            .overlay(OverlayTexture.DEFAULT_UV)
            .light(light)
            .normal(0f, 0f, 1f)

        buffer.vertex(matrix, -0.2f, 0.2f, 0f)
            .color(255, 255, 255, 255)
            .texture(0f, 1f)
            .overlay(OverlayTexture.DEFAULT_UV)
            .light(light)
            .normal(0f, 0f, 1f)

        matrices.pop()
    }
}