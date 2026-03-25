package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import de.fuballer.mcendgame.client.component.render.CustomRenderLayers;
import de.fuballer.mcendgame.client.component.render.ghostly.GhostlySettings;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import de.fuballer.mcendgame.main.util.ColorUtil;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    @ModifyReturnValue(
            method = "getRenderLayer",
            at = @At("RETURN")
    )
    private RenderLayer modifyRenderLayer(@Nullable RenderLayer original, S state) {
        if (!(state instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return original;
        if (!accessor.mcendgame$isGhostly()) return original;

        return CustomRenderLayers.INSTANCE.ghostly(getTexture(state));
    }

    @ModifyVariable(
            method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V",
            at = @At(value = "STORE"),
            ordinal = 2
    )
    private int modifyColor(int original, S state) {
        if (!(state instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return original;
        if (!accessor.mcendgame$isGhostly()) return original;

        return GhostlySettings.INSTANCE.getCOLOR();
    }
}
