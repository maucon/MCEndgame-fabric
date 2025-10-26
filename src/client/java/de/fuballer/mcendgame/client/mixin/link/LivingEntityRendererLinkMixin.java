package de.fuballer.mcendgame.client.mixin.link;

import de.fuballer.mcendgame.client.accessor.LivingEntityLinkRenderStateAccessor;
import de.fuballer.mcendgame.client.component.entity.custom.data.EntityConnectionPointData;
import de.fuballer.mcendgame.client.component.entity.custom.data.MultipleEntityConnectionData;
import de.fuballer.mcendgame.client.mixin.renderer.EntityRendererAccessorMixin;
import de.fuballer.mcendgame.main.accessor.LivingEntityLinkAttributeAccessor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
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
        var yaw = (float) (entity.lerpYaw(tickDelta) * (Math.PI / 180));
        data.setOffset(entity.getLeashOffset(tickDelta).rotateY(-yaw));
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
        originEntity.setPos(entity.getLeashPos(tickDelta));

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

            entityData.setPos(entity.getLeashPos(tickDelta));

            var blockPos = BlockPos.ofFloored(entity.getCameraPosVec(tickDelta));
            entityData.setBlockLight(world.getLightLevel(LightType.BLOCK, blockPos));
            entityData.setSkyLight(world.getLightLevel(LightType.SKY, blockPos));
            entityData.setConnectionDuration(currentTime - entry.getValue());

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
            renderLink(matrixStack, vertexConsumerProvider, data.getOriginEntity(), entity, data.getOffset());
        }
    }

    @Unique
    private void renderLink(
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            EntityConnectionPointData origin,
            EntityConnectionPointData linked,
            Vec3d offset
    ) {
        matrixStack.push();
        matrixStack.translate(offset);

        var distance = linked.getPos().subtract(origin.getPos());

        var vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getLeash());
        var matrix = matrixStack.peek().getPositionMatrix();

        vertexConsumer.vertex(matrix, 0f, 0f, 0f)
                .color(1f, 0f, 0f, 1f).light(0);
        vertexConsumer.vertex(matrix, (float) distance.x, (float) distance.y, (float) distance.z)
                .color(0f, 0f, 1f, 1f).light(0);
        vertexConsumer.vertex(matrix, 0.1f, 0f, 0.1f)
                .color(1f, 0f, 0f, 1f).light(0);
        vertexConsumer.vertex(matrix, (float) distance.x + 0.1f, (float) distance.y, (float) distance.z + 0.1f)
                .color(0f, 0f, 1f, 1f).light(0);
        matrixStack.pop();
    }
}
