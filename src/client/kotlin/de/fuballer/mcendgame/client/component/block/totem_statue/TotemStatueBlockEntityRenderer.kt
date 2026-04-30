package de.fuballer.mcendgame.client.component.block.totem_statue

import de.fuballer.mcendgame.main.component.block.blocks.totem_statue.TotemStatueBlockEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.render.command.ModelCommandRenderer
import net.minecraft.client.render.command.OrderedRenderCommandQueue
import net.minecraft.client.render.state.CameraRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.state.property.Properties
import net.minecraft.util.math.RotationAxis
import net.minecraft.util.math.RotationPropertyHelper
import net.minecraft.util.math.Vec3d
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin

private val TEXTURE = IdentifierUtil.default("textures/block/totem_statue.png")
private val ACTIVE_TEXTURE = IdentifierUtil.default("textures/block/totem_statue_active.png")

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
) : BlockEntityRenderer<TotemStatueBlockEntity, TotemStatueBlockEntityRenderState> {
    private val model: TotemStatueBlockEntityModel

    init {
        val loadedModels = context.loadedEntityModels
        model = TotemStatueBlockEntityModel(loadedModels.getModelPart(TotemStatueBlockEntityModel.MODEL_LAYER))
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

    override fun createRenderState(): TotemStatueBlockEntityRenderState = TotemStatueBlockEntityRenderState()

    override fun updateRenderState(
        blockEntity: TotemStatueBlockEntity,
        state: TotemStatueBlockEntityRenderState,
        tickProgress: Float,
        cameraPos: Vec3d,
        crumblingOverlay: ModelCommandRenderer.CrumblingOverlayCommand?
    ) {
        super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay)

        state.rotation = blockEntity.cachedState.get(Properties.ROTATION)
        state.activeTicks = blockEntity.getActiveTicks()
    }

    override fun render(
        state: TotemStatueBlockEntityRenderState,
        matrices: MatrixStack,
        queue: OrderedRenderCommandQueue,
        cameraState: CameraRenderState,
    ) {
        matrices.push()
        matrices.translate(0.5F, 0.0F, 0.5F)
        matrices.scale(-1.0F, -1.0F, 1.0F)

        val rotation = state.rotation
        val rotationDeg = RotationPropertyHelper.toDegrees(rotation)
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationDeg))

        val activeTicks = state.activeTicks
        if (activeTicks > 0) {
            val preciseTick = activeTicks + MinecraftClient.getInstance().renderTickCounter.getTickProgress(false)

            val hoverOffset = getHoverOffset(preciseTick)
            matrices.translate(0.0, -hoverOffset, 0.0)

            val hoverRot = getHoverRotation(preciseTick)
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(hoverRot))
        }

        val modelState = TotemStatueBlockEntityModel.TotemStatueModelState()
        val texture = if (activeTicks >= 0) ACTIVE_TEXTURE else TEXTURE
        val renderLayer = RenderLayers.entityCutout(texture)
        queue.submitModel(
            model,
            modelState,
            matrices,
            renderLayer,
            state.lightmapCoordinates,
            OverlayTexture.DEFAULT_UV,
            -1,
            null,
            0,
            state.crumblingOverlay
        )

        matrices.pop()
    }
}