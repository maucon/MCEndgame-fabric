package de.fuballer.mcendgame.client.component.render.link

import de.fuballer.mcendgame.client.component.entity.custom.data.EntityConnectionPointData
import de.fuballer.mcendgame.client.component.render.CustomRenderLayers
import de.fuballer.mcendgame.client.messaging.AfterEntitiesRenderCommand
import de.fuballer.mcendgame.client.messaging.CreateLinkDataCommand
import de.fuballer.mcendgame.client.messaging.RenderLinksCommand
import de.fuballer.mcendgame.main.accessor.LivingEntityLinkAttributeAccessor
import de.fuballer.mcendgame.main.component.custom_attribute.effects.link.LinkSettings
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.LightmapTextureManager
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.LightType
import net.minecraft.world.World
import org.joml.Matrix4f
import java.util.*
import kotlin.math.abs
import kotlin.math.sin

@Injectable
class LinkRenderService {
    @CommandHandler
    fun on(cmd: AfterEntitiesRenderCommand) {
        val context = cmd.context

        val client = MinecraftClient.getInstance()
        val player = client.player ?: return
        if (!client.options.perspective.isFirstPerson) return

        val linkedEntities = (player as LivingEntityLinkAttributeAccessor).`mcendgame$getLinkedEntities`()
        val tickDelta = context.tickCounter().getTickProgress(false)

        val createDataCommand = CreateLinkDataCommand(linkedEntities, player, tickDelta)
        val createDataCmd = CommandGateway.apply(createDataCommand)

        val data = createDataCmd.data
        data.offset = data.offset.subtract(0.0, player.getEyeHeight(player.pose).toDouble(), 0.0)

        val matrixStack = context.matrixStack() ?: return
        val vertexConsumerProvider = context.consumers() ?: return
        val age = player.age + tickDelta

        val cmd = RenderLinksCommand(matrixStack, vertexConsumerProvider, data, age)
        CommandGateway.apply(cmd)
    }

    @CommandHandler
    fun on(cmd: CreateLinkDataCommand) {
        val entity = cmd.entity
        val world = entity.world
        val data = cmd.data
        val tickDelta = cmd.tickDelta

        data.offset = Vec3d(0.0, entity.height * 0.7, 0.0)
        data.originEntity = getLinkOriginEntityData(entity, tickDelta, world)
        data.connectedEntities = getLinkedEntitiesData(cmd.linkedEntities, tickDelta, world)
    }

    private fun getLinkOriginEntityData(
        entity: LivingEntity,
        tickDelta: Float,
        world: World,
    ): EntityConnectionPointData {
        val originEntity = EntityConnectionPointData()
        originEntity.pos = entity.getLerpedPos(tickDelta).add(0.0, entity.height * 0.7, 0.0)

        val blockPos = BlockPos.ofFloored(entity.getCameraPosVec(tickDelta))
        originEntity.blockLight = world.getLightLevel(LightType.BLOCK, blockPos)
        originEntity.skyLight = world.getLightLevel(LightType.SKY, blockPos)

        return originEntity
    }

    private fun getLinkedEntitiesData(
        entities: Map<UUID, Long>,
        tickDelta: Float,
        world: World,
    ): List<EntityConnectionPointData> {
        val data = mutableListOf<EntityConnectionPointData>()

        val currentTime = world.time
        for ((uuid, connectionTime) in entities) {
            val entity = world.getEntity(uuid)
            if (entity == null) continue

            val entityData = EntityConnectionPointData()
            entityData.pos = entity.getLerpedPos(tickDelta).add(0.0, entity.height * 0.7, 0.0)

            val blockPos = BlockPos.ofFloored(entity.getCameraPosVec(tickDelta))
            entityData.blockLight = world.getLightLevel(LightType.BLOCK, blockPos)
            entityData.skyLight = world.getLightLevel(LightType.SKY, blockPos)
            entityData.connectionDuration = currentTime + tickDelta - connectionTime

            data.add(entityData)
        }

        return data
    }

    @CommandHandler
    fun on(cmd: RenderLinksCommand) {
        val data = cmd.data
        data.connectedEntities.forEach {
            renderLink(cmd.matrixStack, cmd.vertexConsumerProvider, data.originEntity, it, data.offset, cmd.age)
        }
    }

    private fun renderLink(
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        origin: EntityConnectionPointData,
        linked: EntityConnectionPointData,
        offset: Vec3d,
        age: Float,
    ) {
        matrixStack.push();
        matrixStack.translate(offset);

        val targetDistanceVector = linked.pos.subtract(origin.pos)
        val targetDistance = targetDistanceVector.length()
        val distancePercent = (linked.connectionDuration.toDouble() / LinkSettings.getLinkConnectingTime(targetDistance)).coerceAtMost(1.0)
        val linkDistance = targetDistanceVector.multiply(distancePercent)
        val segmentCount = linkDistance.length() / LinkSettings.LINK_RENDER_SEGMENT_LENGTH

        val perpendicularVector = linkDistance.horizontal.rotateY(Math.toRadians(90.0).toFloat()).normalize()

        val vertexConsumer = vertexConsumerProvider.getBuffer(CustomRenderLayers.LINK)
        val matrix = matrixStack.peek().positionMatrix

        val linkLength = linkDistance.length()

        val vertexData = mutableListOf<LinkVertexData>()
        for (i in 0..segmentCount.toInt() + 1) {
            var vertexDistance = i * LinkSettings.LINK_RENDER_SEGMENT_LENGTH
            if (i > segmentCount + 1) vertexDistance -= LinkSettings.LINK_RENDER_SEGMENT_LENGTH * (1 - segmentCount % 1)
            var vertexPos = linkDistance.multiply(vertexDistance / linkLength)

            val vertexTargetDistancePercentage = vertexDistance / targetDistanceVector.length()
            val flatteningSineStrength = sin(Math.PI * vertexTargetDistancePercentage)
            val flattenedSine = sin(vertexDistance - age * LinkSettings.LINK_RENDER_SINE_SPEED) * flatteningSineStrength

            val verticalOffset = flattenedSine * LinkSettings.LINK_RENDER_SINE_VERTICAL_STRENGTH
            vertexPos = vertexPos.add(0.0, verticalOffset, 0.0)
            val horizontalOffset = flattenedSine * LinkSettings.LINK_RENDER_SINE_HORIZONTAL_STRENGTH
            vertexPos = vertexPos.add(perpendicularVector.multiply(horizontalOffset))

            val thicknessFactor = 1 + (abs(flattenedSine) * LinkSettings.LINK_RENDER_MAX_THICKNESS_FACTOR)

            val distancePercentage = vertexDistance / targetDistance
            val color = LinkSettings.getColor(distancePercentage)

            val blockLight = MathHelper.lerp(distancePercentage.toFloat(), origin.blockLight, linked.blockLight)
            val skyLight = MathHelper.lerp(distancePercentage.toFloat(), origin.skyLight, linked.skyLight)
            val light = LightmapTextureManager.pack(blockLight, skyLight)

            vertexData.add(LinkVertexData(vertexPos, color, light, thicknessFactor))
        }

        val widthOffset = perpendicularVector.multiply(LinkSettings.LINK_RENDER_SEGMENT_WIDTH)

        vertexData.forEach { data -> addVertices(vertexConsumer, matrix, data, widthOffset, false) }
        vertexData.reversed().forEach { data -> addVertices(vertexConsumer, matrix, data, widthOffset, true) }

        matrixStack.pop();
    }

    private fun addVertices(
        vertexConsumer: VertexConsumer,
        matrix: Matrix4f,
        data: LinkVertexData,
        widthOffset: Vec3d,
        reverse: Boolean,
    ) {
        val pos = data.pos
        val color = data.color
        val light = data.light

        val thickness = data.thicknessFactor
        val heightOffset = (if (reverse) LinkSettings.LINK_RENDER_SEGMENT_WIDTH else -LinkSettings.LINK_RENDER_SEGMENT_WIDTH) * thickness
        val scaledWidthOffset = widthOffset.multiply(thickness)

        val vec1 = pos.add(scaledWidthOffset).add(0.0, heightOffset, 0.0)
        vertexConsumer.vertex(matrix, vec1.x.toFloat(), vec1.y.toFloat(), vec1.z.toFloat())
            .color(color).light(light)

        val vec2 = pos.subtract(scaledWidthOffset).subtract(0.0, heightOffset, 0.0)
        vertexConsumer.vertex(matrix, vec2.x.toFloat(), vec2.y.toFloat(), vec2.z.toFloat())
            .color(color).light(light)
    }
}