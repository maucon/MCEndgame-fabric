package de.fuballer.mcendgame.client.mixin.living_entity.isolated;

import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateIsolatedAccessor;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributeUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Mixin(GeoEntityRenderer.class)
public class GeoEntityRendererIsolatedMixin {
    @Inject(
            method = "extractLivingEntityRenderState",
            at = @At("TAIL")
    )
    void extractLivingEntityRenderState(
            LivingEntity entity,
            LivingEntityRenderState renderState,
            float partialTick,
            ItemModelManager itemModelResolver,
            CallbackInfo ci
    ) {
        if (!(entity instanceof MobEntity)) return;
        if (!(renderState instanceof LivingEntityRenderStateIsolatedAccessor renderStateAccessor)) return;

        var player = MinecraftClient.getInstance().player;
        var isolated = player != null && CustomAttributeUtil.INSTANCE.isIsolated(entity, player);
        renderStateAccessor.mcendgame$setIsolated(isolated);
    }
}
