package de.fuballer.mcendgame.client.mixin.biped;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends BipedEntityRenderState> {
    @Inject(method = "setAngles(Lnet/minecraft/client/render/entity/state/BipedEntityRenderState;)V", at = @At("HEAD"))
    public void setAngles(T bipedEntityRenderState, CallbackInfo ci) {
        if (bipedEntityRenderState instanceof PlayerEntityRenderState && ((PlayerEntityRenderState) bipedEntityRenderState).spectator) return;

        var accessor = (BipedEntityRenderStateAccessor) bipedEntityRenderState;
        var model = (BipedEntityModel<?>) (Object) this;

        var hiddenParts = accessor.mcendgame$getHiddenParts();
        var legsVisible = !Boolean.TRUE.equals(hiddenParts.get(BipedEntityRenderStateAccessor.HideAblePart.LEGS));
        model.leftLeg.visible = legsVisible;
        model.rightLeg.visible = legsVisible;
    }
}
