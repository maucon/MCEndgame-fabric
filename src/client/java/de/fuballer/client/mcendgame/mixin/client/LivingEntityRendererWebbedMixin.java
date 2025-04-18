package de.fuballer.client.mcendgame.mixin.client;

import de.fuballer.client.mcendgame.accessor.LivingEntityRenderStateWebbedAccessor;
import de.fuballer.mcendgame.accessor.LivingEntityWebbedAccessor;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererWebbedMixin<T extends LivingEntity, S extends LivingEntityRenderState> {
    @Inject(method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V", at = @At("HEAD"))
    void updateRenderState(T livingEntity, S livingEntityRenderState, float f, CallbackInfo ci) {
        if (!(livingEntity instanceof LivingEntityWebbedAccessor entityAccessor)) return;
        if (!(livingEntityRenderState instanceof LivingEntityRenderStateWebbedAccessor renderStateAccessor)) return;
        renderStateAccessor.mcendgame$setWebbed(entityAccessor.mcendgame$isWebbed());
    }
}
