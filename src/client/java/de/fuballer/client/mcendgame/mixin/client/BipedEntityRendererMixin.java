package de.fuballer.client.mcendgame.mixin.client;

import de.fuballer.client.mcendgame.mixin_interfaces.BipedEntityRenderStateAccessor;
import de.fuballer.mcendgame.components.item.custom.armor.CustomArmorItems;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityRenderer.class)
public class BipedEntityRendererMixin {
    @Inject(method = "updateBipedRenderState", at = @At("TAIL"))
    private static void updateBipedRenderState(
            LivingEntity entity,
            BipedEntityRenderState state,
            float tickDelta,
            ItemModelManager itemModelResolver,
            CallbackInfo ci
    ) {
        var accessor = (BipedEntityRenderStateAccessor) state;
        var wearsLamiasGift = state.equippedLegsStack.isOf(CustomArmorItems.INSTANCE.getLAMIAS_GIFT());
        accessor.mcendgame$setHideLegs(wearsLamiasGift);
        accessor.mcendgame$setHideBoots(wearsLamiasGift);
    }
}
