package de.fuballer.mcendgame.main.mixin.damage.spear;

import de.fuballer.mcendgame.main.context.PierceContext;
import net.minecraft.component.type.PiercingWeaponComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiercingWeaponComponent.class)
public class PiercingWeaponComponentMixin {
    @Inject(
            method = "stab",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;pierce(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/entity/Entity;FZZZ)Z")
    )
    public void setWeaponType(LivingEntity attacker, EquipmentSlot slot, CallbackInfo ci) {
        PierceContext.CURRENT.set(PierceContext.PierceType.PIERCE);
    }
}