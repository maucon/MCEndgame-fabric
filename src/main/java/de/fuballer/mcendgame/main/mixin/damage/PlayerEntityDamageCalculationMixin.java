package de.fuballer.mcendgame.main.mixin.damage;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
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

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityDamageCalculationMixin extends LivingEntity {
    protected PlayerEntityDamageCalculationMixin(EntityType<? extends LivingEntity> entityType, World world) {
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

    /**
     * As we calculate all damage increases and also reductions and mitigations in the
     * LivingEntityDamageMixin#damage method we need to remove any kind of mitigation
     * of this method, except for absorption amount.
     */
    @Inject(at = @At("HEAD"), method = "applyDamage", cancellable = true)
    protected void applyDamage(
            ServerWorld world,
            DamageSource source,
            float amount,
            CallbackInfo ci
    ) {
        PlayerEntity this_ = (PlayerEntity) (Object) this;

        // region original
        if (this.isInvulnerableTo(world, source)) {
            return;
        }

        // amount = this.applyArmorToDamage(source, amount);
        // float finalDamageAfterMitigation = amount = this.modifyAppliedDamage(source, amount);
        ///////////////////////////////////////////////////////////////////////////////////
        var finalDamageAfterMitigation = amount;
        ///////////////////////////////////////////////////////////////////////////////////

        amount = Math.max(amount - this.getAbsorptionAmount(), 0.0f);
        this.setAbsorptionAmount(this.getAbsorptionAmount() - (finalDamageAfterMitigation - amount));
        float g = finalDamageAfterMitigation - amount;
        if (g > 0.0f && g < 3.4028235E37f) {
            this_.increaseStat(Stats.DAMAGE_ABSORBED, Math.round(g * 10.0f));
        }
        if (amount == 0.0f) {
            return;
        }
        this_.addExhaustion(source.getExhaustion());
        this.getDamageTracker().onDamage(source, amount);
        this.setHealth(this.getHealth() - amount);
        if (amount < 3.4028235E37f) {
            this_.increaseStat(Stats.DAMAGE_TAKEN, Math.round(amount * 10.0f));
        }
        this.emitGameEvent(GameEvent.ENTITY_DAMAGE);
        // endregion

        ci.cancel();
    }
}
