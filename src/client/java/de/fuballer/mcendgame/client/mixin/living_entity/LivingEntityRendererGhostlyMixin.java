package de.fuballer.mcendgame.client.mixin.living_entity;

import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import de.fuballer.mcendgame.client.component.item.custom.armor.geistergaloschen.GhostlyVertexConsumer;
import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererGhostlyMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> {
    @Shadow
    public abstract Identifier getTexture(S state);

    @Inject(
            method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V",
            at = @At("TAIL")
    )
    public void updateRenderState(
            LivingEntity livingEntity,
            LivingEntityRenderState livingEntityRenderState,
            float f,
            CallbackInfo ci
    ) {
        if (!(livingEntityRenderState instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return;

        var ghostly = CustomAttributesExtensions.INSTANCE.isGhostly(livingEntity);
        accessor.mcendgame$setGhostly(ghostly);
    }

    @Redirect(
            method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V")
    )
    private void setRenderColor(
            M model,
            MatrixStack matrices,
            VertexConsumer vertices,
            int light,
            int overlay,
            int color,
            S livingEntityRenderState,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider
    ) {
        if (!EntityRenderStateMixinExtension.INSTANCE.isGhostly(livingEntityRenderState)) {
            model.render(matrices, vertices, light, overlay, color);
            return;
        }

        var texture = getTexture(livingEntityRenderState);
        var translucentVertices = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(texture));
        var ghostlyVertices = new GhostlyVertexConsumer(translucentVertices);
        model.render(matrices, ghostlyVertices, light, OverlayTexture.DEFAULT_UV);
    }
}
