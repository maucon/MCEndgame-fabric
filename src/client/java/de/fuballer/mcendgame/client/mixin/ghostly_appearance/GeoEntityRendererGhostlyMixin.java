package de.fuballer.mcendgame.client.mixin.ghostly_appearance;

import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateGhostlyAccessor;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

@Mixin(GeoEntityRenderer.class)
public class GeoEntityRendererGhostlyMixin<R extends EntityRenderState & GeoRenderState> {
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
}
