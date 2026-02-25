package de.fuballer.mcendgame.client.component.entity.custom.feature.isolated

import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension.isIsolated
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributeUtil.canSeeIsolated
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.RotationAxis

private val ICON_TEXTURE = IdentifierUtil.default("textures/indicator/isolated.png")

object IsolatedIndicatorRenderer {
    fun tryRender(
        state: LivingEntityRenderState,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        isGeoEntity: Boolean = false,
    ) {
        if (state.deathTime > 0) return

        if (!state.isIsolated()) return
        val player = MinecraftClient.getInstance().player ?: return
        if (!player.canSeeIsolated()) return

        renderIcon(state, matrices, vertexConsumers, light, isGeoEntity)
    }

    private fun renderIcon(
        state: LivingEntityRenderState,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        isGeoEntity: Boolean,
    ) {
        matrices.push()

        if (isGeoEntity) matrices.scale(-1f, -1f, 1f) // revert geckolibs axis flips

        val yOffset = (if (isGeoEntity) 0.0 else 1.5) - state.entityType.height - 0.5 // 1.5 moves origin to feet for vanilla entities
        matrices.translate(0.0, yOffset, 0.0)

        val camera = MinecraftClient.getInstance().gameRenderer.camera
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(state.bodyYaw - camera.yaw))
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.pitch))

        val buffer = vertexConsumers.getBuffer(RenderLayers.entityCutoutNoCull(ICON_TEXTURE))
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