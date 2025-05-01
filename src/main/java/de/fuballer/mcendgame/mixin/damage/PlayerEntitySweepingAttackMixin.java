package de.fuballer.mcendgame.mixin.damage;

import de.fuballer.mcendgame.components.damage.custom.CustomDamageTypes;
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
                    target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"
            ),
            index = 1
    )
    private DamageSource modifyServerAttack(DamageSource original) {
        var world = Objects.requireNonNull(original.getAttacker()).getWorld();

        return CustomDamageTypes.INSTANCE.of(
                world,
                CustomDamageTypes.INSTANCE.getSWEEPING(),
                original.getAttacker(),
                original.getSource()
        );
    }
}
