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
            val f = 0.025f
            val g = (hookedData.pos.x - webHookData.pos.x).toFloat()
            val h = (hookedData.pos.y - webHookData.pos.y).toFloat()
            val i = (hookedData.pos.z - webHookData.pos.z).toFloat()
            val j = MathHelper.inverseSqrt(g * g + i * i) * f / 2.0f
            val k = i * j
            val l = g * j
            matrices.push()
            matrices.translate(webHookData.offset)
            val vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getLeash())
            val matrix4f = matrices.peek().positionMatrix

            for (m in 0..24) {
                renderWebHookSegment(
                    vertexConsumer,
                    matrix4f,
                    g,
                    h,
                    i,
                    hookedData.blockLight,
                    webHookData.blockLight,
                    hookedData.skyLight,
                    webHookData.skyLight,
                    0.025f,
                    0.025f,
                    k,
                    l,
                    m,
                    false
                )
            }

            for (m in 24 downTo 0) {
                renderWebHookSegment(
                    vertexConsumer,
                    matrix4f,
                    g,
                    h,
                    i,
                    hookedData.blockLight,
                    webHookData.blockLight,
                    hookedData.skyLight,
                    webHookData.skyLight,
                    0.025f,
                    0.0f,
                    k,
                    l,
                    m,
                    true
                )
            }

            matrices.pop()
        }
    }

    private fun renderWebHookSegment(
        vertexConsumer: VertexConsumer,
        matrix: Matrix4f,
        leashedEntityX: Float,
        leashedEntityY: Float,
        leashedEntityZ: Float,
        leashedEntityBlockLight: Int,
        leashHolderBlockLight: Int,
        leashedEntitySkyLight: Int,
        leashHolderSkyLight: Int,
        f: Float,
        g: Float,
        h: Float,
        i: Float,
        segmentIndex: Int,
        isLeashKnot: Boolean
    ) {
        val j = segmentIndex.toFloat() / 24.0f
        val k = MathHelper.lerp(j, leashedEntityBlockLight.toFloat(), leashHolderBlockLight.toFloat()).toInt()
        val l = MathHelper.lerp(j, leashedEntitySkyLight.toFloat(), leashHolderSkyLight.toFloat()).toInt()
        val m = LightmapTextureManager.pack(k, l)
        val n = if (segmentIndex % 2 == (if (isLeashKnot) 1 else 0)) 0.7f else 1.0f
        val o = 0.5f * n
        val p = 0.4f * n
        val q = 0.3f * n
        val r = leashedEntityX * j
        val s =
            if (leashedEntityY > 0.0f) leashedEntityY * j * j else leashedEntityY - leashedEntityY * (1.0f - j) * (1.0f - j)
        val t = leashedEntityZ * j
        vertexConsumer.vertex(matrix, r - h, s + g, t + i).color(o, p, q, 1.0f).light(m)
        vertexConsumer.vertex(matrix, r + h, s + f - g, t - i).color(o, p, q, 1.0f).light(m)
    }
}