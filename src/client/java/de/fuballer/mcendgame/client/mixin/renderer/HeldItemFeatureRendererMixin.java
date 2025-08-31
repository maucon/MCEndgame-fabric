package de.fuballer.mcendgame.client.mixin.renderer;

import de.fuballer.mcendgame.client.component.item.custom.armor.geistergaloschen.GhostlyVertexConsumer;
import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HeldItemFeatureRenderer.class)
public class HeldItemFeatureRendererMixin<S extends ArmedEntityRenderState> {
    @Redirect(
            method = "renderItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderState;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V")
    )
    void render(
            ItemRenderState itemState,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay,
            S entityState
    ) {
        if (!EntityRenderStateMixinExtension.INSTANCE.isGhostly(entityState)) itemState.render(matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);
        else {
            var ghostlyVertexConsumerProvider = GhostlyVertexConsumer.Companion.convertToGhostlyVertexConsumerProvider(vertexConsumers);
            itemState.render(matrices, ghostlyVertexConsumerProvider, light, OverlayTexture.DEFAULT_UV);
        }
    }
}