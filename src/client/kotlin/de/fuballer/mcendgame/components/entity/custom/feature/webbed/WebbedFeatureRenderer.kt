package de.fuballer.mcendgame.components.entity.custom.feature.webbed

import de.fuballer.mcendgame.mixin_interfaces.LivingEntityRenderStateWebbedAccessor
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.state.LivingEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import org.joml.Vector3f
import kotlin.math.max

class WebbedFeatureRenderer<T : LivingEntityRenderState, M : EntityModel<T>>(
    featureContext: FeatureRendererContext<T, M>,
    ctx: EntityRendererFactory.Context,
) : FeatureRenderer<T, M>(featureContext) {
    val model = WebbedModel(ctx.getPart(WebbedModel.WEBBED_LAYER))

    companion object {
        private val TEXTURE = IdentifierUtil.default("textures/entity/feature/webbed/webbed.png")
    }

    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        state: T,
        limbAngle: Float,
        limbDistance: Float
    ) {
        if (!isWebbed(state)) return

        matrices.push()

        // players are rendered smaller for some fucking reason
        // prob some other mobs too
        val invScale = 1F / getMatrixStackHeightScale(matrices)

        // 1.5 reverts translate in LivingEntityRenderer
        val height = max(state.standingEyeHeight * 0.875F, state.height * 0.6F)
        val translateY = 1.5F - (height * invScale)
        matrices.translate(0F, translateY, 0F)

        val scaleX = state.width * 1.1F * invScale
        val scaleY = state.standingEyeHeight * 0.75F * invScale
        matrices.scale(scaleX, scaleY, scaleX)

        val vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(TEXTURE))
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)

        matrices.pop()
    }

    private fun getMatrixStackHeightScale(matrices: MatrixStack): Float {
        val entry = matrices.peek()
        val modelMatrix = entry.positionMatrix

        //val xAxis = Vector3f(modelMatrix.m00(), modelMatrix.m01(), modelMatrix.m02())
        val yAxis = Vector3f(modelMatrix.m10(), modelMatrix.m11(), modelMatrix.m12())
        //val zAxis = Vector3f(modelMatrix.m20(), modelMatrix.m21(), modelMatrix.m22())

        return yAxis.length()
    }

    private fun isWebbed(state: T): Boolean {
        val accessor = state as? LivingEntityRenderStateWebbedAccessor ?: return false
        return accessor.`mcendgame$isWebbed`()
    }
}