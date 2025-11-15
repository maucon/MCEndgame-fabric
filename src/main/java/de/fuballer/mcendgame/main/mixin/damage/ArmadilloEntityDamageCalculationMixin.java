package de.fuballer.mcendgame.main.mixin.damage;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.ArmadilloEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmadilloEntity.class)
public abstract class ArmadilloEntityDamageCalculationMixin extends LivingEntity {
    protected ArmadilloEntityDamageCalculationMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * The original implementation of this method is used in the
     * LivingEntityDamageMixin#damage method to bring together all the values needed
     * to calculate the damage in our custom system.
     */
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    protected void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        var value = super.damage(world, source, amount);
        cir.setReturnValue(value);
    }
}
