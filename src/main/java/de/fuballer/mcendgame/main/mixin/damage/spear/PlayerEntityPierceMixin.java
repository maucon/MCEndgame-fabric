package de.fuballer.mcendgame.main.mixin.damage.spear;

import de.fuballer.mcendgame.main.component.damage.custom_type.CustomDamageTypes;
import de.fuballer.mcendgame.main.context.PierceContext;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public class PlayerEntityPierceMixin {
    @ModifyArg(
            method = "pierce",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;sidedDamage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            ),
            index = 0
    )
    private DamageSource modifyDamageSource(DamageSource original) {
        var world = Objects.requireNonNull(original.getAttacker()).getEntityWorld();
        var pierceType = Objects.requireNonNull(PierceContext.CURRENT.get());
        PierceContext.CURRENT.remove();

        var damageType = switch (pierceType) {
            case PIERCE -> CustomDamageTypes.INSTANCE.getPIERCE_ATTACK();
            case KINETIC -> CustomDamageTypes.INSTANCE.getKINETIC_ATTACK();
        };

        return CustomDamageTypes.INSTANCE.of(world, damageType, original.getAttacker(), original.getSource());
    }
}
