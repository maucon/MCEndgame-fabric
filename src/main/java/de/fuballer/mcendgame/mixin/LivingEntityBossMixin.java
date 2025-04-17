package de.fuballer.mcendgame.mixin;

import de.fuballer.mcendgame.accessors.MobEntityBossAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityBossMixin {
    @Inject(method = "damage", at = @At("HEAD"))
    void damage(
            ServerWorld world,
            DamageSource source,
            float amount,
            CallbackInfoReturnable<Boolean> cir
    ) {
        var livingEntity = (LivingEntity) (Object) this;

        if (!(livingEntity instanceof MobEntity mobEntity)) return;
        if (!mobEntity.isAiDisabled()) return;

        if (!(mobEntity instanceof MobEntityBossAccessor bossAccessor)) return;
        if (!bossAccessor.mcendgame$isDungeonBoss()) return;

        mobEntity.setAiDisabled(false);

        if (!(source.getAttacker() instanceof LivingEntity attacker)) return;
        if (attacker instanceof PlayerEntity && attacker.isInCreativeMode()) return;
        mobEntity.setTarget(attacker);
    }
}
