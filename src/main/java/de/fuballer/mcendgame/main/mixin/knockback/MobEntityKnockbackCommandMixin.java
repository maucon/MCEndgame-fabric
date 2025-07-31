package de.fuballer.mcendgame.main.mixin.knockback;

import de.fuballer.mcendgame.main.component.custom_attribute.effects.knockback.AttackKnockbackUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobEntity.class)
public class MobEntityKnockbackCommandMixin {
    @Redirect(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;takeKnockback(DDD)V"))
    void redirectTakeKnockback(LivingEntity instance, double strength, double x, double z) {
        var mobEntity = (MobEntity) (Object) this;
        AttackKnockbackUtil.INSTANCE.takeKnockbackFrom(instance, mobEntity, strength, x, z);
    }
}
