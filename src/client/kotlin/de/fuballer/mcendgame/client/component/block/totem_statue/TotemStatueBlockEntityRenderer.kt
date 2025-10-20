package de.fuballer.mcendgame.client.component.block.totem_statue

import de.fuballer.mcendgame.main.component.block.totem_statue.TotemStatueBlockEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.model.Model
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.state.property.Properties
import net.minecraft.util.math.RotationAxis
import net.minecraft.util.math.RotationPropertyHelper
import net.minecraft.util.math.Vec3d
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin

private const val HOVER_START_TICKS = 50
private const val HOVER_HEIGHT = 0.3
private const val HOVER_CYCLE_TICKS = 50
private const val HOVER_HEIGHT_DIFFERENCE = 0.1

private const val SLOPE_AT_CYCLE_START = 1.0 / HOVER_CYCLE_TICKS

// used for start of hover animation to smoothly transition into sin wave [f(x)=px^3+qx^2]
private val X3_COEFFICIENT = (HOVER_START_TICKS * SLOPE_AT_CYCLE_START - 2 * HOVER_HEIGHT) / HOVER_START_TICKS.toDouble().pow(3) //p
private val X2_COEFFICIENT = (3 * HOVER_HEIGHT - HOVER_START_TICKS * SLOPE_AT_CYCLE_START) / HOVER_START_TICKS.toDouble().pow(2) //q

private const val DEG_ROTATION_PER_TICK = 5F

class TotemStatueBlockEntityRenderer(
    context: BlockEntityRendererFactory.Context,
) : BlockEntityRenderer<TotemStatueBlockEntity> {
    private val model: Model
    private val texture = IdentifierUtil.default("textures/block/totem_statue.png")

    init {
        val loadedModels = context.loadedEntityModels
        model = TotemStatueBlockEntityModel(loadedModels.getModelPart(TotemStatueBlockEntityModel.MODEL_LAYER))
    }

    override fun render(
        entity: TotemStatueBlockEntity,
        tickProgress: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int,
        cameraPos: Vec3d,
    ) {
        matrices.push()
        matrices.translate(0.5F, 0.0F, 0.5F)
        matrices.scale(-1.0F, -1.0F, 1.0F)

        val state = entity.cachedState
        val rotation = state.get(Properties.ROTATION)
        val rotationDeg = RotationPropertyHelper.toDegrees(rotation)
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationDeg))

        val activeTicks = entity.getActiveTicks()
        if (activeTicks > 0) {
            val preciseTick = activeTicks + tickProgress

            val hoverOffset = getHoverOffset(preciseTick)
            matrices.translate(0.0, -hoverOffset, 0.0)

            val hoverRot = getHoverRotation(preciseTick)
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(hoverRot))
        }

        val renderLayer = RenderLayer.getEntityCutout(texture)
        val vertexConsumer = vertexConsumers.getBuffer(renderLayer)

        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)

        matrices.pop()
    }

    private fun getHoverOffset(ticks: Float): Double {
        if (ticks < 0) return 0.0

        if (ticks < HOVER_START_TICKS) return X3_COEFFICIENT * ticks.toDouble().pow(3) + X2_COEFFICIENT * ticks.toDouble().pow(2)

        val waveTicks = ticks - HOVER_START_TICKS
        val waveProgress = waveTicks.toDouble() / HOVER_CYCLE_TICKS
        val waveOffset = HOVER_HEIGHT_DIFFERENCE * sin(2 * PI * waveProgress)

        return HOVER_HEIGHT + waveOffset
    }

    private fun getHoverRotation(ticks: Float): Float {
        val deg = ticks * DEG_ROTATION_PER_TICK
        val slowStartFactor = 1 - exp(-ticks * 0.01F)
        return deg * slowStartFactor
    }
}