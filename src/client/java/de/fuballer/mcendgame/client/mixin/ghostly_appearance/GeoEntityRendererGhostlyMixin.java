package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import de.fuballer.mcendgame.client.component.render.CustomRenderLayers;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import de.fuballer.mcendgame.main.util.ColorUtil;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

@Mixin(GeoEntityRenderer.class)
public class GeoEntityRendererGhostlyMixin<T extends Entity & GeoAnimatable, R extends EntityRenderState & GeoRenderState> {
    @Inject(method = "extractLivingEntityRenderState", at = @At("HEAD"))
    void extractLivingEntityRenderState(
            LivingEntity entity,
            LivingEntityRenderState renderState,
            float partialTick,
            ItemModelManager itemModelResolver,
            CallbackInfo ci
    ) {
        if (!(renderState instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return;

        var ghostly = CustomAttributesExtensions.INSTANCE.isGhostly(entity);
        accessor.mcendgame$setGhostly(ghostly);
    }

    @Inject(
            method = "getRenderType(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;",
            at = @At("HEAD"),
            cancellable = true
    )
    void modifyRenderLayer(R renderState, Identifier texture, CallbackInfoReturnable<RenderLayer> cir) {
        if (!(renderState instanceof LivingEntityRenderStateGhostlyAccessor accessor)) return;
        if (!accessor.mcendgame$isGhostly()) return;

        cir.setReturnValue(CustomRenderLayers.INSTANCE.ghostly(texture));
        cir.cancel();
    }

    @ModifyVariable(
            method = "getRenderColor(Lnet/minecraft/entity/Entity;Ljava/lang/Void;F)I",
            at = @At("STORE"),
            name = "color"
    )
    int modifyRenderColor(int color, T animatable) {
        if (!(animatable instanceof LivingEntity livingEntity)) return color;
        if (!CustomAttributesExtensions.INSTANCE.isGhostly(livingEntity)) return color;
        return ColorUtil.INSTANCE.rgbaToInt(10, 235, 200, 120);
    }
}
