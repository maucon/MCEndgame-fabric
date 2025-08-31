package de.fuballer.mcendgame.client.mixin.renderer;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.client.util.EntityRenderStateMixinExtension;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(HeadFeatureRenderer.class)
public class HeadFeatureRendererGhostlyMixin<S extends LivingEntityRenderState> {
    @ModifyVariable(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/LivingEntityRenderState;FF)V",
            at = @At("STORE"),
            ordinal = 0
    )
    RenderLayer getRenderLayer(
            RenderLayer renderLayer,
            @Local(argsOnly = true) S livingEntityRenderState,
            @Local SkullBlock.SkullType skullType
    ) {
        if (!EntityRenderStateMixinExtension.INSTANCE.isGhostly(livingEntityRenderState)) return renderLayer;

        var profile = livingEntityRenderState.wearingSkullProfile;
        var texture = getTexture(skullType, profile);
        return RenderLayer.getEntityTranslucent(texture);
    }

    @Unique
    private Identifier getTexture(
            SkullBlock.SkullType type,
            @Nullable ProfileComponent profile
    ) {
        if (type == SkullBlock.Type.PLAYER && profile != null) {
            var skinProvider = MinecraftClient.getInstance().getSkinProvider();
            return skinProvider.getSkinTextures(profile.gameProfile()).texture();
        }

        return SkullBlockEntityRendererAccessorMixin.getTextures().get(type);
    }
}
