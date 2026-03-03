package de.fuballer.mcendgame.client.mixin.phasing;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public class BackgroundRendererBlockPhasingMixin {
    @Unique
    private static final Vector4f PHASING_FOG_COLOR = new Vector4f(0.2F, 0.7F, 0.6F, 1F);

    @Inject(
            method = "getFogColor",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getFogColor(
            Camera camera,
            float tickProgress,
            ClientWorld world,
            int clampedViewDistance,
            float skyDarkness,
            CallbackInfoReturnable<Vector4f> cir
    ) {
        Entity entity = camera.getFocusedEntity();
        if (!EntityExtension.INSTANCE.isPhasingThroughWall(entity)) return;
        cir.setReturnValue(PHASING_FOG_COLOR);
    }

    @Inject(
            method = "applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/fog/FogRenderer;applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V"
            )
    )
    private void modifyFogData(
            Camera camera,
            int viewDistance,
            RenderTickCounter tickCounter,
            float skyDarkness,
            ClientWorld world,
            CallbackInfoReturnable<Vector4f> cir,
            @Local FogData fogData
    ) {
        Entity entity = camera.getFocusedEntity();
        if (entity == null || !EntityExtension.INSTANCE.isPhasingThroughWall(entity)) return;

        fogData.environmentalStart = 0F;
        fogData.environmentalEnd = 4F;
        fogData.renderDistanceStart = 0F;
        fogData.renderDistanceEnd = 4F;
    }
}
