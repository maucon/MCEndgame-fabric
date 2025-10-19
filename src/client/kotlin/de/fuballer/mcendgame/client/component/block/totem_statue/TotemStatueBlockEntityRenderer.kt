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

        val renderLayer = RenderLayer.getEntityCutout(texture)
        val vertexConsumer = vertexConsumers.getBuffer(renderLayer)

        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)

        matrices.pop()
    }
}