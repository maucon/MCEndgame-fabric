package de.fuballer.mcendgame.main.mixin.knockback;

import de.fuballer.mcendgame.main.component.custom_attribute.effects.knockback.AttackKnockbackUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityKnockbackCommandMixin {
    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;takeKnockback(DDD)V"))
    void redirectTakeKnockbackInDamage(LivingEntity instance, double strength, double x, double z, ServerWorld world, DamageSource source, float amount) {
        AttackKnockbackUtil.INSTANCE.takeKnockbackFrom(instance, source.getAttacker(), strength, x, z);
    }

    @Redirect(method = "knockback", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;takeKnockback(DDD)V"))
    void redirectTakeKnockbackInKnockback(LivingEntity instance, double strength, double x, double z, LivingEntity target) {
        AttackKnockbackUtil.INSTANCE.takeKnockbackFrom(target, instance, strength, x, z);
    }
}