package de.fuballer.mcendgame.client.mixin.player;

import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.HidePlayerModelPartArmor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "isPartVisible", at = @At("HEAD"), cancellable = true)
    public void isPartVisible(PlayerModelPart modelPart, CallbackInfoReturnable<Boolean> cir) {
        var playerEntity = (PlayerEntity) (Object) this;

        var armorItems = List.of(
                playerEntity.getEquippedStack(EquipmentSlot.HEAD).getItem(),
                playerEntity.getEquippedStack(EquipmentSlot.CHEST).getItem(),
                playerEntity.getEquippedStack(EquipmentSlot.LEGS).getItem(),
                playerEntity.getEquippedStack(EquipmentSlot.FEET).getItem()
        );

        for (Item item : armorItems) {
            if (!(item instanceof HidePlayerModelPartArmor armor)) continue;

            if (!armor.hidesModelPart(modelPart)) continue;
            cir.setReturnValue(false);
            return;
        }
    }
}
