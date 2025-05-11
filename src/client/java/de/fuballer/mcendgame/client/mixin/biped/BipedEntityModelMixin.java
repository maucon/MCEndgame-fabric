package de.fuballer.mcendgame.client.mixin.biped;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.entity.EntityType;
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
        var entityType = bipedEntityRenderState.entityType;

        var hiddenParts = accessor.mcendgame$getHiddenParts();

        var bodyVisible = !Boolean.TRUE.equals(hiddenParts.get(BipedEntityRenderStateAccessor.HideAblePart.BODY));
        model.body.visible = bodyVisible;

        var armsVisible = !Boolean.TRUE.equals(hiddenParts.get(BipedEntityRenderStateAccessor.HideAblePart.ARMS))
                || entityType == EntityType.ARMOR_STAND
                || entityType == EntityType.SKELETON
                || entityType == EntityType.STRAY
                || entityType == EntityType.BOGGED
                || entityType == EntityType.WITHER_SKELETON;
        model.leftArm.visible = armsVisible;
        model.rightArm.visible = armsVisible;

        var legsVisible = !Boolean.TRUE.equals(hiddenParts.get(BipedEntityRenderStateAccessor.HideAblePart.LEGS));
        model.leftLeg.visible = legsVisible;
        model.rightLeg.visible = legsVisible;
    }
}
