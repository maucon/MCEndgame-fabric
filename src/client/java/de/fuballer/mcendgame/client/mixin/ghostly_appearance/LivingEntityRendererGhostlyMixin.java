package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import de.fuballer.mcendgame.client.component.item.custom.armor.geistergaloschen.GhostlyVertexConsumer;
import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @ModifyArg(
            method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"),
            index = 1
    )
    VertexConsumer renderModel(
            VertexConsumer vertexConsumer,
            @Local(argsOnly = true) S livingEntityRenderState
    ) {
        if (!EntityRenderStateMixinExtension.INSTANCE.isGhostly(livingEntityRenderState)) return vertexConsumer;
        return new GhostlyVertexConsumer(vertexConsumer);
    }

    @ModifyArg(
            method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/FeatureRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/EntityRenderState;FF)V"),
            index = 1
    )
    VertexConsumerProvider renderFeature(
            VertexConsumerProvider vertexConsumers,
            @Local(argsOnly = true) S livingEntityRenderState
    ) {
        if (!EntityRenderStateMixinExtension.INSTANCE.isGhostly(livingEntityRenderState)) return vertexConsumers;
        return layer -> new GhostlyVertexConsumer(vertexConsumers.getBuffer(layer));
    }

    @Inject(
            method = "getRenderLayer",
            at = @At("HEAD"),
            cancellable = true)
    void getRenderLayer(
            S state,
            boolean showBody,
            boolean translucent,
            boolean showOutline,
            CallbackInfoReturnable<RenderLayer> cir
    ) {
        if (!EntityRenderStateMixinExtension.INSTANCE.isGhostly(state)) return;

        var texture = getTexture(state);
        var renderLayer = RenderLayer.getEntityTranslucent(texture);
        cir.setReturnValue(renderLayer);
    }
}
