package de.fuballer.mcendgame.client.mixin.biped;

import de.fuballer.mcendgame.client.accessor.BipedEntityRenderStateAccessor;
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideBipedBoneArmor;
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HideOtherArmorArmor;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.Collectors;

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

        var armorItems = List.of(
                entity.getEquippedStack(EquipmentSlot.HEAD).getItem(),
                entity.getEquippedStack(EquipmentSlot.CHEST).getItem(),
                entity.getEquippedStack(EquipmentSlot.LEGS).getItem(),
                entity.getEquippedStack(EquipmentSlot.FEET).getItem()
        );

        var hiddenBones = armorItems.stream()
                .filter(item -> item instanceof HideBipedBoneArmor)
                .flatMap(item -> ((HideBipedBoneArmor) item).getHiddenBones().stream())
                .collect(Collectors.toSet());
        accessor.mcendgame$setHiddenBones(hiddenBones);

        var hiddenArmor = armorItems.stream()
                .filter(item -> item instanceof HideOtherArmorArmor)
                .flatMap(item -> ((HideOtherArmorArmor) item).getHiddenArmor().stream())
                .collect(Collectors.toSet());
        accessor.mcendgame$setHiddenArmor(hiddenArmor);
    }
}
