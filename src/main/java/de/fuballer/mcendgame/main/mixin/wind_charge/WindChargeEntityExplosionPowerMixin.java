package de.fuballer.mcendgame.main.mixin.wind_charge;

import de.fuballer.mcendgame.main.accessor.WindChargeEntityExplosionPowerAccessor;
import net.minecraft.entity.projectile.WindChargeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WindChargeEntity.class)
public class WindChargeEntityExplosionPowerMixin implements WindChargeEntityExplosionPowerAccessor {
    @Unique
    private float explosionPower = Float.NaN;

    @ModifyArg(
            method = "createExplosion",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/World$ExplosionSourceType;Lnet/minecraft/particle/ParticleEffect;Lnet/minecraft/particle/ParticleEffect;Lnet/minecraft/util/collection/Pool;Lnet/minecraft/registry/entry/RegistryEntry;)V"
            )
    )
    float a(float originalPower) {
        return Float.isNaN(explosionPower) ? originalPower : explosionPower;
    }

    @Override
    public void mcendgame$setExplosionPower(float power) {
        explosionPower = power;
    }
}
