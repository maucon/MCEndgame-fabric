package de.fuballer.client.mcendgame.mixin.client;

import de.fuballer.client.mcendgame.mixin_interfaces.LivingEntityAccessor;
import de.fuballer.client.mcendgame.mixin_interfaces.LivingEntityRenderStateAccessor;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @Inject(at = @At("TAIL"), method = "updateRenderState")
    public void updateRenderState(
            LivingEntity livingEntity,
            LivingEntityRenderState livingEntityRenderState,
            float f,
            CallbackInfo ci
    ) {
        if (!(livingEntityRenderState instanceof LivingEntityRenderStateAccessor livingEntityRenderStateAccessor))
            return;
        livingEntityRenderStateAccessor.mcendgame$setHealth(livingEntity.getHealth());
        livingEntityRenderStateAccessor.mcendgame$setMaxHealth(livingEntity.getMaxHealth());

        if (!(livingEntity instanceof LivingEntityAccessor livingEntityAccessor)) return;
        livingEntityRenderStateAccessor.mcendgame$setLowHealthTicks20(livingEntityAccessor.mcendgame$getLowHealthTicks20());
    }
}
