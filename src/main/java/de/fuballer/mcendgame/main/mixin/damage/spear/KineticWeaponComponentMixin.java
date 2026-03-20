package de.fuballer.mcendgame.main.mixin.damage.spear;

import de.fuballer.mcendgame.main.context.PierceContext;
import net.minecraft.component.type.KineticWeaponComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KineticWeaponComponent.class)
public class KineticWeaponComponentMixin {
    @Inject(
            method = "usageTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;pierce(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/entity/Entity;FZZZ)Z")
    )
    public void setPierceType(ItemStack stack, int remainingUseTicks, LivingEntity user, EquipmentSlot slot, CallbackInfo ci) {
        PierceContext.CURRENT.set(PierceContext.PierceType.KINETIC);
    }
}