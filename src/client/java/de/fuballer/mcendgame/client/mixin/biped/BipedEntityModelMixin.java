package de.fuballer.mcendgame.client.mixin.biped;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideBipedBoneArmor;
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

        var hiddenBones = accessor.mcendgame$getHiddenBones();

        model.head.visible = !hiddenBones.contains(HideBipedBoneArmor.BipedBone.HEAD);
        model.body.visible = !hiddenBones.contains(HideBipedBoneArmor.BipedBone.BODY);
        var armsVisible = !hiddenBones.contains(HideBipedBoneArmor.BipedBone.ARMS);
        model.leftArm.visible = armsVisible;
        model.rightArm.visible = armsVisible;
        var legsVisible = !hiddenBones.contains(HideBipedBoneArmor.BipedBone.LEGS);
        model.leftLeg.visible = legsVisible;
        model.rightLeg.visible = legsVisible;
    }
}
