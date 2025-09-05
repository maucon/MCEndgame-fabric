package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CapeFeatureRenderer.class)
public class CapeFeatureRendererGhostlyMixin {
    @ModifyArg(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/PlayerEntityRenderState;FF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;")
    )
    RenderLayer getRenderLayer(
            RenderLayer layer,
            @Local(argsOnly = true) PlayerEntityRenderState playerEntityRenderState,
            @Local SkinTextures skinTextures
    ) {
        if (!(playerEntityRenderState instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return layer;
        if (!accessor.mcendgame$isGhostly()) return layer;

        return RenderLayer.getEntityTranslucent(skinTextures.capeTexture());
    }
}
