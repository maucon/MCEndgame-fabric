package de.fuballer.mcendgame.client.mixin.living_entity;

import de.fuballer.mcendgame.client.accessor.LivingEntityRenderStateIsolatedAccessor;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributeUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererIsolatedMixin<T extends LivingEntity, S extends LivingEntityRenderState> {
    @Inject(method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V", at = @At("HEAD"))
    void updateRenderState(T livingEntity, S livingEntityRenderState, float f, CallbackInfo ci) {
        if (!(livingEntity instanceof MobEntity)) return;
        if (!(livingEntityRenderState instanceof LivingEntityRenderStateIsolatedAccessor renderStateAccessor)) return;

        var player = MinecraftClient.getInstance().player;
        var isolated = player != null && CustomAttributeUtil.INSTANCE.isIsolated(livingEntity, player);
        renderStateAccessor.mcendgame$setIsolated(isolated);
    }
}
