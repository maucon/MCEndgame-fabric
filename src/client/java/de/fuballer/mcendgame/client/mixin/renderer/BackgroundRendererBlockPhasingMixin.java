package de.fuballer.mcendgame.client.mixin.renderer;

import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererBlockPhasingMixin {
    @Unique
    private static final Vector4f PHASING_FOG_COLOR = new Vector4f(0.2F, 0.7F, 0.6F, 1F);

    @Inject(
            method = "getFogColor",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getFogColor(
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
            method = "applyFog",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/BackgroundRenderer;getFogModifier(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/client/render/BackgroundRenderer$StatusEffectFogModifier;"),
            cancellable = true)
    private static void applyFog(
            Camera camera,
            BackgroundRenderer.FogType fogType,
            Vector4f color,
            float viewDistance,
            boolean thickenFog,
            float tickProgress,
            CallbackInfoReturnable<Fog> cir,
            @Local Entity entity
    ) {
        if (!EntityExtension.INSTANCE.isPhasingThroughWall(entity)) return;

        var fogStart = 0F;
        var fogEnd = 0F;

        if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
            fogStart = 0F;
            fogEnd = 4F;
        } else if (fogType == BackgroundRenderer.FogType.FOG_TERRAIN) {
            fogStart = 1.25F;
            fogEnd = 5F;
        }

        var fog = new Fog(fogStart, fogEnd, FogShape.SPHERE, PHASING_FOG_COLOR.x, PHASING_FOG_COLOR.y, PHASING_FOG_COLOR.z, PHASING_FOG_COLOR.w);
        cir.setReturnValue(fog);
    }
}
