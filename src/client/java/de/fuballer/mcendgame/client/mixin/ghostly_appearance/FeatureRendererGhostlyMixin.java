package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import de.fuballer.mcendgame.client.component.render.CustomRenderLayers;
import de.fuballer.mcendgame.client.component.render.ghostly.GhostlySettings;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FeatureRenderer.class)
public class FeatureRendererGhostlyMixin {
    @ModifyArg(
            method = "renderModel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/command/RenderCommandQueue;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/RenderLayer;IIILnet/minecraft/client/texture/Sprite;ILnet/minecraft/client/render/command/ModelCommandRenderer$CrumblingOverlayCommand;)V"),
            index = 3
    )
    private static <S extends LivingEntityRenderState> RenderLayer setGhostlyRenderLayer(
            RenderLayer original,
            @Local(argsOnly = true) Identifier texture,
            @Local(argsOnly = true) S state
    ) {
        if (!(state instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return original;
        if (!accessor.mcendgame$isGhostly()) return original;

        return CustomRenderLayers.INSTANCE.ghostly(texture);
    }

    @ModifyArg(
            method = "renderModel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/command/RenderCommandQueue;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/RenderLayer;IIILnet/minecraft/client/texture/Sprite;ILnet/minecraft/client/render/command/ModelCommandRenderer$CrumblingOverlayCommand;)V"),
            index = 6
    )
    private static <S extends LivingEntityRenderState> int setGhostlyColor(
            int original,
            @Local(argsOnly = true) S state
    ) {
        if (!(state instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return original;
        if (!accessor.mcendgame$isGhostly()) return original;

        return GhostlySettings.INSTANCE.getCOLOR();
    }
}
