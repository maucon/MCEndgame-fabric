package de.fuballer.mcendgame.client.mixin.biped;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import de.fuballer.mcendgame.main.component.item.custom.armor.CustomArmorItems;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
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

        var leggings = entity.getEquippedStack(EquipmentSlot.LEGS);
        var wearsLamiasGift = leggings.isOf(CustomArmorItems.INSTANCE.getLAMIAS_GIFT());

        var hideLegs = wearsLamiasGift;
        accessor.mcendgame$setHiddenPart(BipedEntityRenderStateAccessor.HideAblePart.LEGS, hideLegs);

        var hideBoots = wearsLamiasGift;
        accessor.mcendgame$setHiddenPart(BipedEntityRenderStateAccessor.HideAblePart.BOOTS, hideBoots);
    }
}
