package de.fuballer.mcendgame.components.entity.custom.entities.arachne

import de.fuballer.mcendgame.components.entity.custom.entities.mount.MountEntity
import de.fuballer.mcendgame.util.IdentifierUtil
import net.minecraft.client.render.LightmapTextureManager
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.LightType
import org.joml.Matrix4f

class ArachneRenderer(
    context: EntityRendererFactory.Context,
) : MobEntityRenderer<ArachneEntity, ArachneRenderState, ArachneEntityModel>(
    context,
    ArachneEntityModel(context.getPart(ArachneEntityModel.ARACHNE)),
    0.8F //shadow
) {
    override fun createRenderState(): ArachneRenderState =
        ArachneRenderState()

    override fun getTexture(state: ArachneRenderState): Identifier {
        if (state.isSaddled) return IdentifierUtil.default("textures/entity/arachne/arachne_saddled.png")
        return IdentifierUtil.default("textures/entity/arachne/arachne.png")
    }

    override fun updateRenderState(
        entity: ArachneEntity,
        renderState: ArachneRenderState,
        tickDelta: Float
    ) {
        super.updateRenderState(entity, renderState, tickDelta)

        renderState.idleAnimationState.copyFrom(entity.idleAnimationState)
        renderState.walkAnimationState.copyFrom(entity.walkAnimationState)
        renderState.walkBWAnimationState.copyFrom(entity.walkBWAnimationState)

        renderState.spitAnimationState.copyFrom(entity.spitAnimationState)

        renderState.isSaddled = entity.isSaddled

        renderState.moveSpeed = entity.dataTracker.get(MountEntity.ANIMATION_MOVEMENT_SPEED)

        updateHookedRenderState(entity, renderState, tickDelta)
    }

    private fun updateHookedRenderState(
        entity: ArachneEntity,
        renderState: ArachneRenderState,
        tickDelta: Float
    ) {
        val webHookData = ArachneRenderState.Companion.WebHookData()
        renderState.webHookData = webHookData

        if (entity.hookedEntities.isEmpty()) return

        val yaw = entity.lerpYaw(tickDelta) * (Math.PI / 180.0).toFloat()
        webHookData.offset = entity.getLeashOffset(tickDelta).rotateY(-yaw)
        webHookData.pos = entity.getLeashPos(tickDelta)

        val blockPos = BlockPos.ofFloored(entity.getCameraPosVec(tickDelta))
        webHookData.blockLight = getBlockLight(entity, blockPos)
        webHookData.skyLight = entity.world.getLightLevel(LightType.SKY, blockPos)

        val hookedEntityDataList = mutableListOf<ArachneRenderState.Companion.WebHookedEntityData>()
        for (hookedEntity in entity.hookedEntities) {
            val hookedEntityData = ArachneRenderState.Companion.WebHookedEntityData()

            hookedEntityData.pos = hookedEntity.getLeashPos(tickDelta)

            val hookedBlockPos = BlockPos.ofFloored(hookedEntity.getCameraPosVec(tickDelta))
            hookedEntityData.blockLight = hookedEntity.world.getLightLevel(LightType.BLOCK, hookedBlockPos)
            hookedEntityData.skyLight = entity.world.getLightLevel(LightType.SKY, hookedBlockPos)

            hookedEntityDataList.add(hookedEntityData)
        }
        webHookData.hookedEntities = hookedEntityDataList
    }

    override fun render(
        state: ArachneRenderState,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
    ) {
        super.render(state, matrices, vertexConsumers, light)

        val webHookData = state.webHookData ?: return
        renderWebHook(matrices, vertexConsumers, webHookData)
    }

    private fun renderWebHook(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        webHookData: ArachneRenderState.Companion.WebHookData
    ) {
        for (hookedData in webHookData.hookedEntities) {
            val hookedOffset = hookedData.pos.subtract(webHookData.pos)

            val segmentSize = 0.025f
            val horizontalSizeFactor = 1F / hookedOffset.horizontalLength() * segmentSize
            val segmentSizeX = (hookedOffset.x * horizontalSizeFactor).toFloat()
            val segmentSizeZ = (hookedOffset.z * horizontalSizeFactor).toFloat()

            matrices.push()
            matrices.translate(webHookData.offset)
            val vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getLeash())
            val matrix4f = matrices.peek().positionMatrix

            for (segment in 0..24) {
                renderWebHookSegment(
                    vertexConsumer,
                    matrix4f,
                    hookedOffset,
                    hookedData.blockLight,
                    webHookData.blockLight,
                    hookedData.skyLight,
                    webHookData.skyLight,
                    segmentSize,
                    segmentSizeZ,
                    segmentSizeX,
                    segment,
                    false,
                )
            }

            for (segment in 24 downTo 0) {
                renderWebHookSegment(
                    vertexConsumer,
                    matrix4f,
                    hookedOffset,
                    hookedData.blockLight,
                    webHookData.blockLight,
                    hookedData.skyLight,
                    webHookData.skyLight,
                    segmentSize,
                    segmentSizeZ,
                    segmentSizeX,
                    segment,
                    true,
                )
            }

            matrices.pop()
        }
    }

    private fun renderWebHookSegment(
        vertexConsumer: VertexConsumer,
        matrix: Matrix4f,
        hookedEntityOffset: Vec3d, // relative pos
        leashedEntityBlockLight: Int,
        leashHolderBlockLight: Int,
        leashedEntitySkyLight: Int,
        leashHolderSkyLight: Int,
        segmentSizeY: Float,
        segmentSizeX: Float,
        segmentSizeZ: Float,
        segmentIndex: Int,
        rotated: Boolean,
    ) {
        val segmentPercent = segmentIndex.toFloat() / 24.0f
        val blockLight = MathHelper.lerp(segmentPercent, leashedEntityBlockLight, leashHolderBlockLight)
        val skyLight = MathHelper.lerp(segmentPercent, leashedEntitySkyLight, leashHolderSkyLight)
        val light = LightmapTextureManager.pack(blockLight, skyLight)

        val brightnessFactor = if (segmentIndex % 2 == (if (rotated) 1 else 0)) 0.7f else 1.0f
        val red = 0.85f * brightnessFactor
        val green = 0.85f * brightnessFactor
        val blue = 0.85f * brightnessFactor

        val segmentX = (hookedEntityOffset.x * segmentPercent).toFloat()
        val segmentY =
            (if (hookedEntityOffset.y > 0.0f) hookedEntityOffset.y * segmentPercent * segmentPercent else hookedEntityOffset.y - hookedEntityOffset.y * (1.0f - segmentPercent) * (1.0f - segmentPercent)).toFloat()
        val segmentZ = (hookedEntityOffset.z * segmentPercent).toFloat()

        vertexConsumer.vertex(
            matrix,
            segmentX - segmentSizeX / 2,
            segmentY + if (rotated) segmentSizeY else 0F,
            segmentZ + segmentSizeZ / 2
        ).color(red, green, blue, 1.0f).light(light)
        vertexConsumer.vertex(
            matrix,
            segmentX + segmentSizeX / 2,
            segmentY + if (rotated) 0F else segmentSizeY,
            segmentZ - segmentSizeZ / 2
        ).color(red, green, blue, 1.0f).light(light)
    }
}