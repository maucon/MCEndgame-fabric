package de.fuballer.mcendgame.main.mixin.damage;

import de.fuballer.mcendgame.main.component.damage.custom_type.CustomDamageTypes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntitySweepingAttackMixin {
    @ModifyArg(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;doSweepingAttack(Lnet/minecraft/entity/Entity;FLnet/minecraft/entity/damage/DamageSource;F)V"
            ),
            index = 2
    )
    private DamageSource modifyServerAttack(DamageSource original) {
        var world = Objects.requireNonNull(original.getAttacker()).getEntityWorld();

        return CustomDamageTypes.INSTANCE.of(
                world,
                CustomDamageTypes.INSTANCE.getSWEEPING(),
                original.getAttacker(),
                original.getSource()
        );
    }
}
