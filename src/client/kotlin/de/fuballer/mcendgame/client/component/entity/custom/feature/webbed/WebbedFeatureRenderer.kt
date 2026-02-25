package de.fuballer.mcendgame.client.component.entity.custom.feature.webbed

import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension.isWebbed
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.command.OrderedRenderCommandQueue
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
        queue: OrderedRenderCommandQueue,
        light: Int,
        state: T,
        limbAngle: Float,
        limbDistance: Float
    ) {
        if (!state.isWebbed()) return

        matrices.push()

        // Players are rendered smaller due to an unknown scaling factor.
        // This might also apply to other mobs.
        val invScale = 1F / getMatrixStackHeightScale(matrices)

        // 1.5 reverts translate in LivingEntityRenderer
        val height = max(state.standingEyeHeight * 0.875F, state.height * 0.6F)
        val translateY = 1.5F - (height * invScale)
        matrices.translate(0F, translateY, 0F)

        val scaleX = state.width * 1.1F * invScale
        val scaleY = state.standingEyeHeight * 0.75F * invScale * (2F / 3F) // model is 1.5 blocks
        matrices.scale(scaleX, scaleY, scaleX)

        queue.submitModel(
            model,
            WebbedModel.WebbedData(),
            matrices,
            RenderLayers.entityCutout(TEXTURE),
            light,
            OverlayTexture.DEFAULT_UV,
            state.outlineColor,
            null,
        )

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
}