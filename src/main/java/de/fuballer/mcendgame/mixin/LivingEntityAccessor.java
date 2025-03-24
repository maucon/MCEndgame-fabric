package de.fuballer.mcendgame.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    @Accessor("riptideAttackDamage")
    float getRiptideAttackDamage();

    @Invoker("getKnockbackAgainst")
    float getKnockbackAgainst(Entity target, DamageSource damageSource);
}