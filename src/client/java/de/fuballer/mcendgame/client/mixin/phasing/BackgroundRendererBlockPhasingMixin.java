package de.fuballer.mcendgame.client.mixin.phasing;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.ByteBuffer;

@Mixin(FogRenderer.class)
public abstract class BackgroundRendererBlockPhasingMixin {
    @Shadow
    protected abstract void applyFog(ByteBuffer buffer, int bufPos, Vector4f fogColor, float environmentalStart, float environmentalEnd, float renderDistanceStart, float renderDistanceEnd, float skyEnd, float cloudEnd);

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
        if (!EntityExtension.INSTANCE.isBlockPhasingAtEyes(entity)) return;
        cir.setReturnValue(PHASING_FOG_COLOR);
    }

    @Redirect(
            method = "applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/fog/FogRenderer;applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V")
    )
    void applyBlockPhasingFog(
            FogRenderer instance,
            ByteBuffer buffer,
            int bufPos,
            Vector4f fogColor,
            float environmentalStart,
            float environmentalEnd,
            float renderDistanceStart,
            float renderDistanceEnd,
            float skyEnd,
            float cloudEnd,
            @Local Entity entity
    ) {
        if (entity == null || !EntityExtension.INSTANCE.isBlockPhasingAtEyes(entity)) {
            applyFog(buffer, bufPos, fogColor, environmentalStart, environmentalEnd, renderDistanceStart, renderDistanceEnd, skyEnd, cloudEnd);
        } else {
            applyFog(buffer, bufPos, fogColor, 0F, 4F, 0F, 4F, 4F, 4F);
        }
    }
}
