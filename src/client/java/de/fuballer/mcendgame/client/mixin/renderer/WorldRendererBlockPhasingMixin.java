package de.fuballer.mcendgame.client.mixin.renderer;

import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererBlockPhasingMixin {
    @Inject(
            method = "renderSky",
            at = @At("HEAD"),
            cancellable = true)
    void renderSky(
            FrameGraphBuilder frameGraphBuilder,
            Camera camera,
            float tickProgress,
            Fog fog,
            CallbackInfo ci
    ) {
        var entity = camera.getFocusedEntity();
        if (!EntityExtension.INSTANCE.isPhasingThroughWall(entity)) return;
        ci.cancel();
    }
}
