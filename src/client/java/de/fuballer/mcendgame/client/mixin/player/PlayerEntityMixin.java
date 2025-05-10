package de.fuballer.mcendgame.client.mixin.player;

import de.fuballer.mcendgame.main.component.item.custom.armor.CustomArmorItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "isPartVisible", at = @At("HEAD"), cancellable = true)
    public void isPartVisible(PlayerModelPart modelPart, CallbackInfoReturnable<Boolean> cir) {
        var playerEntity = (PlayerEntity) (Object) this;

        if (modelPart == PlayerModelPart.LEFT_PANTS_LEG || modelPart == PlayerModelPart.RIGHT_PANTS_LEG) {
            var leggings = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            var hidePants = leggings.isOf(CustomArmorItems.INSTANCE.getWITHER_ROSE_LEGGINGS());

            if (hidePants) {
                cir.setReturnValue(false);
                return;
            }
        }
    }
}
