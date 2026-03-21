package de.fuballer.mcendgame.client.mixin.phasing;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererBlockPhasingMixin {
    @ModifyExpressionValue(
            method = "renderOverlays",
            at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerEntity;noClip:Z")
    )
    private static boolean doNotRenderInWallOverlay(
            boolean original,
            @Local PlayerEntity playerEntity
    ) {
        if (CustomAttributesExtensions.INSTANCE.hasBlockPhasing(playerEntity)) return true;
        return original;
    }
}
