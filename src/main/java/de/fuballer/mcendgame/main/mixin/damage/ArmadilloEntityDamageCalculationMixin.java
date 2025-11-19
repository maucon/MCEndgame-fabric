package de.fuballer.mcendgame.main.mixin.damage;

import de.fuballer.mcendgame.main.component.damage.dealing.ExtendedDamageSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.ArmadilloEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ArmadilloEntity.class)
public abstract class ArmadilloEntityDamageCalculationMixin extends LivingEntity {
    protected ArmadilloEntityDamageCalculationMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract boolean isNotIdle();

    @ModifyVariable(
            method = "damage",
            at = @At("HEAD"),
            argsOnly = true
    )
    private DamageSource onModifyDamageSource(DamageSource source) {
        var extendedSource = source instanceof ExtendedDamageSource
                ? (ExtendedDamageSource) source
                : new ExtendedDamageSource(source);

        extendedSource.getDamageCalculationConfig().armadilloDamageReduction(isNotIdle());
        return extendedSource;
    }
}
