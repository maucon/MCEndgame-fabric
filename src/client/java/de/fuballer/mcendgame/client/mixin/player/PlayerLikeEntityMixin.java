package de.fuballer.mcendgame.client.mixin.player;

import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HidePlayerModelPartArmor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerLikeEntity.class)
public class PlayerLikeEntityMixin {
    @Inject(method = "isModelPartVisible", at = @At("HEAD"), cancellable = true)
    public void isModelPartVisible(PlayerModelPart modelPart, CallbackInfoReturnable<Boolean> cir) {
        var playerLikeEntity = (PlayerLikeEntity) (Object) this;

        var armorItems = List.of(
                playerLikeEntity.getEquippedStack(EquipmentSlot.HEAD).getItem(),
                playerLikeEntity.getEquippedStack(EquipmentSlot.CHEST).getItem(),
                playerLikeEntity.getEquippedStack(EquipmentSlot.LEGS).getItem(),
                playerLikeEntity.getEquippedStack(EquipmentSlot.FEET).getItem()
        );

        for (Item item : armorItems) {
            if (!(item instanceof HidePlayerModelPartArmor armor)) continue;

            if (!armor.hidesModelPart(modelPart)) continue;
            cir.setReturnValue(false);
            return;
        }
    }
}
