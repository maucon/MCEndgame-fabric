package de.fuballer.mcendgame.client.component.entity.custom.entities.arachne

import de.fuballer.mcendgame.client.component.entity.custom.data.EntityConnectionPointData
import de.fuballer.mcendgame.client.component.entity.custom.data.MultipleEntityConnectionData
import de.fuballer.mcendgame.main.component.entity.custom.entities.arachne.ArachneEntity
import de.fuballer.mcendgame.main.component.entity.custom.entities.mount.DirectionalMovementEntity
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import net.minecraft.client.render.Frustum
import net.minecraft.client.render.LightmapTextureManager
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.command.OrderedRenderCommandQueue
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.client.render.state.CameraRenderState
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
        renderState.meleeAttackAnimationState.copyFrom(entity.attackAnimationState)

        renderState.isSaddled = entity.hasSaddleEquipped()

        renderState.moveSpeed = entity.dataTracker.get(DirectionalMovementEntity.ANIMATION_MOVEMENT_SPEED)

        updateHookedRenderState(entity, renderState, tickDelta)
    }

    private fun updateHookedRenderState(
        entity: ArachneEntity,
        renderState: ArachneRenderState,
        tickDelta: Float
    ) {
        val webHookData = MultipleEntityConnectionData()
        renderState.webHookData = webHookData

        if (entity.hookedEntityIds.isEmpty()) return

        val yaw = entity.lerpYaw(tickDelta) * (Math.PI / 180.0).toFloat()
        webHookData.offset = entity.getLeashOffset(tickDelta).rotateY(-yaw)
        webHookData.originEntity.pos = entity.getLeashPos(tickDelta)

        val world = entity.entityWorld

        val blockPos = BlockPos.ofFloored(entity.getCameraPosVec(tickDelta))
        webHookData.originEntity.blockLight = getBlockLight(entity, blockPos)
        webHookData.originEntity.skyLight = world.getLightLevel(LightType.SKY, blockPos)

        val hookedEntityDataList = mutableListOf<EntityConnectionPointData>()
        for (hookedEntityId in entity.hookedEntityIds) {
            val hookedEntity = world.getEntityById(hookedEntityId) ?: continue

            val hookedEntityData = EntityConnectionPointData()

            hookedEntityData.pos = hookedEntity.getLeashPos(tickDelta)

            val hookedBlockPos = BlockPos.ofFloored(hookedEntity.getCameraPosVec(tickDelta))
            hookedEntityData.blockLight = world.getLightLevel(LightType.BLOCK, hookedBlockPos)
            hookedEntityData.skyLight = world.getLightLevel(LightType.SKY, hookedBlockPos)

            hookedEntityDataList.add(hookedEntityData)
        }
        webHookData.connectedEntities = hookedEntityDataList
    }

    override fun render(
        state: ArachneRenderState,
        matrices: MatrixStack,
        orderedRenderCommandQueue: OrderedRenderCommandQueue,
        cameraRenderState: CameraRenderState,
    ) {
        super.render(state, matrices, orderedRenderCommandQueue, cameraRenderState)

        val webHookData = state.webHookData ?: return
        renderWebHook(matrices, orderedRenderCommandQueue, webHookData)
    }

    private fun renderWebHook(
        matrices: MatrixStack,
        queue: OrderedRenderCommandQueue,
        webHookData: MultipleEntityConnectionData,
    ) {
        for (hookedData in webHookData.connectedEntities) {
            val hookedOffset = hookedData.pos.subtract(webHookData.originEntity.pos)

            val segmentSize = 0.05f
            val horizontalSizeFactor = 1F / hookedOffset.horizontalLength() * segmentSize
            val segmentSizeX = (hookedOffset.x * horizontalSizeFactor).toFloat()
            val segmentSizeZ = (hookedOffset.z * horizontalSizeFactor).toFloat()

            matrices.push()
            matrices.translate(webHookData.offset)

            queue.submitCustom(matrices, RenderLayers.leash()) { entry, vertexConsumer ->
                for (segment in 0..24) {
                    renderWebHookSegment(
                        vertexConsumer,
                        entry.positionMatrix,
                        hookedOffset,
                        hookedData.blockLight,
                        webHookData.originEntity.blockLight,
                        hookedData.skyLight,
                        webHookData.originEntity.skyLight,
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
                        entry.positionMatrix,
                        hookedOffset,
                        hookedData.blockLight,
                        webHookData.originEntity.blockLight,
                        hookedData.skyLight,
                        webHookData.originEntity.skyLight,
                        segmentSize,
                        segmentSizeZ,
                        segmentSizeX,
                        segment,
                        true,
                    )
                }
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

        val brightnessFactor = if (segmentIndex % 2 == (if (rotated) 1 else 0)) 0.85f else 0.98f
        val red = 0.99f * brightnessFactor
        val green = 0.95f * brightnessFactor
        val blue = 0.87f * brightnessFactor

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

    override fun shouldRender(
        entity: ArachneEntity,
        frustum: Frustum,
        x: Double,
        y: Double,
        z: Double
    ): Boolean {
        if (super.shouldRender(entity, frustum, x, y, z)) return true

        val world = entity.entityWorld
        for (hookedId in entity.hookedEntityIds) {
            val hookedEntity = world.getEntityById(hookedId) ?: continue
            if (frustum.isVisible(hookedEntity.boundingBox)) return true
        }

        return false
    }
}