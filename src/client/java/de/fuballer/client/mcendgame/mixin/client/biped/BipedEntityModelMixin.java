package de.fuballer.client.mcendgame.mixin.client.biped;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends BipedEntityRenderState> {
    @Shadow
    public abstract void setVisible(boolean visible);

    @Inject(method = "setAngles(Lnet/minecraft/client/render/entity/state/BipedEntityRenderState;)V", at = @At("HEAD"))
    public void setAngles(T bipedEntityRenderState, CallbackInfo ci) {
        setVisible(true);

        var accessor = (BipedEntityRenderStateAccessor) bipedEntityRenderState;
        if (accessor.mcendgame$getHideLegs()) {
            var model = (BipedEntityModel<?>) (Object) this;
            model.leftLeg.visible = false;
            model.rightLeg.visible = false;
        }
    }
}
