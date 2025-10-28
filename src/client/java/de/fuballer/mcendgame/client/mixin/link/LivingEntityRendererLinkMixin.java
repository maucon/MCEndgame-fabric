package de.fuballer.mcendgame.client.mixin.link;

import de.fuballer.mcendgame.client.accessor.LivingEntityLinkRenderStateAccessor;
import de.fuballer.mcendgame.client.component.entity.custom.data.EntityConnectionPointData;
import de.fuballer.mcendgame.client.component.entity.custom.data.MultipleEntityConnectionData;
import de.fuballer.mcendgame.client.mixin.renderer.EntityRendererAccessorMixin;
import de.fuballer.mcendgame.main.accessor.LivingEntityLinkAttributeAccessor;
import de.fuballer.mcendgame.main.component.custom_attribute.effects.link.LinkSettings;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererLinkMixin<T extends LivingEntity, S extends LivingEntityRenderState> {
    @Inject(
            method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V",
            at = @At("TAIL")
    )
    void updateRenderState(T entity, S renderState, float tickDelta, CallbackInfo ci) {
        var linkedEntities = ((LivingEntityLinkAttributeAccessor) entity).mcendgame$getLinkedEntities();
        var renderStateAccessor = (LivingEntityLinkRenderStateAccessor) renderState;
        if (linkedEntities.isEmpty()) {
            renderStateAccessor.mcendgame$setLinksData(new MultipleEntityConnectionData());
            return;
        }

        var world = entity.getWorld();
        var data = new MultipleEntityConnectionData();
        data.setOffset(new Vec3d(0.0, entity.getHeight() * 0.7, 0.0));
        data.setOriginEntity(getLinkOriginEntityData(entity, tickDelta, world));
        data.setConnectedEntities(getLinkedEntitiesData(linkedEntities, tickDelta, world));

        renderStateAccessor.mcendgame$setLinksData(data);
    }

    @Unique
    private EntityConnectionPointData getLinkOriginEntityData(
            T entity,
            float tickDelta,
            World world
    ) {
        var originEntity = new EntityConnectionPointData();
        originEntity.setPos(entity.getLerpedPos(tickDelta).add(0.0, entity.getHeight() * 0.7, 0.0));

        var blockPos = BlockPos.ofFloored(entity.getCameraPosVec(tickDelta));
        var blockLight = ((EntityRendererAccessorMixin<T>) this).callGetBlockLight(entity, blockPos);
        originEntity.setBlockLight(blockLight);
        originEntity.setSkyLight(world.getLightLevel(LightType.SKY, blockPos));

        return originEntity;
    }

    @Unique
    private List<EntityConnectionPointData> getLinkedEntitiesData(
            Map<UUID, Long> entities,
            float tickDelta,
            World world
    ) {
        var data = new ArrayList<EntityConnectionPointData>();

        var currentTime = world.getTime();
        for (Map.Entry<UUID, Long> entry : entities.entrySet()) {
            var entity = world.getEntity(entry.getKey());
            if (entity == null) continue;

            var entityData = new EntityConnectionPointData();
            entityData.setPos(entity.getLerpedPos(tickDelta).add(0.0, entity.getHeight() * 0.7, 0.0));

            var blockPos = BlockPos.ofFloored(entity.getCameraPosVec(tickDelta));
            entityData.setBlockLight(world.getLightLevel(LightType.BLOCK, blockPos));
            entityData.setSkyLight(world.getLightLevel(LightType.SKY, blockPos));
            entityData.setConnectionDuration(currentTime + tickDelta - entry.getValue());

            data.add(entityData);
        }

        return data;
    }

    @Inject(
            method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At("TAIL")
    )
    void render(S renderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo ci) {
        var data = ((LivingEntityLinkRenderStateAccessor) renderState).mcendgame$getLinksData();

        for (EntityConnectionPointData entity : data.getConnectedEntities()) {
            renderLink(renderState, matrixStack, vertexConsumerProvider, data.getOriginEntity(), entity, data.getOffset());
        }
    }

    @Unique
    private void renderLink(
            S renderState,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            EntityConnectionPointData origin,
            EntityConnectionPointData linked,
            Vec3d offset
    ) {
        matrixStack.push();
        matrixStack.translate(offset);

        var targetDistance = linked.getPos().subtract(origin.getPos());
        var distancePercent = Math.min(linked.getConnectionDuration() / LinkSettings.DAMAGE_DELAY, 1f);
        var linkDistance = targetDistance.multiply(distancePercent);
        var segmentCount = linkDistance.length() / LinkSettings.LINK_RENDER_SEGMENT_LENGTH;

        var vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getLeash());
        var matrix = matrixStack.peek().getPositionMatrix();

        var totalLength = linkDistance.length();

        var vertexPositions = new ArrayList<Vec3d>();
        for (int i = 0; i < segmentCount + 2; i++) {
            var vertexDistance = i * LinkSettings.LINK_RENDER_SEGMENT_LENGTH;
            if (i > segmentCount + 1) vertexDistance -= LinkSettings.LINK_RENDER_SEGMENT_LENGTH * (1 - segmentCount % 1);
            var vertexPos = linkDistance.multiply(vertexDistance / totalLength);

            var vertexTargetDistancePercentage = vertexDistance / targetDistance.length();
            var flatteningSineStrength = Math.sin(Math.PI * vertexTargetDistancePercentage);
            var yOffset = Math.sin(vertexDistance - renderState.age * LinkSettings.LINK_RENDER_SINE_SPEED) * LinkSettings.LINK_RENDER_SINE_STRENGTH * flatteningSineStrength;

            vertexPositions.add(vertexPos.add(0.0, yOffset, 0.0));
        }

        var widthOffset = linkDistance.getHorizontal().rotateY((float) Math.toRadians(90.0)).normalize().multiply(LinkSettings.LINK_RENDER_SEGMENT_WIDTH);

        for (Vec3d vertexPosition : vertexPositions) addVertices(vertexConsumer, matrix, vertexPosition, widthOffset, 0, false);
        for (int i = vertexPositions.size() - 1; i >= 0; i--) addVertices(vertexConsumer, matrix, vertexPositions.get(i), widthOffset, 0, true);

        matrixStack.pop();
    }

    @Unique
    private void addVertices(
            VertexConsumer vertexConsumer,
            Matrix4f matrix,
            Vec3d vertexPos,
            Vec3d widthOffset,
            int color,
            boolean reverse
    ) {
        var heightOffset = reverse ? LinkSettings.LINK_RENDER_SEGMENT_WIDTH : -LinkSettings.LINK_RENDER_SEGMENT_WIDTH;

        var vec1 = vertexPos.add(widthOffset).add(0.0, heightOffset, 0.0);
        vertexConsumer.vertex(matrix, (float) vec1.x, (float) vec1.y, (float) vec1.z)
                .color(color).light(0);

        var vec2 = vertexPos.subtract(widthOffset).subtract(0.0, heightOffset, 0.0);
        vertexConsumer.vertex(matrix, (float) vec2.x, (float) vec2.y, (float) vec2.z)
                .color(color).light(0);
    }
}
