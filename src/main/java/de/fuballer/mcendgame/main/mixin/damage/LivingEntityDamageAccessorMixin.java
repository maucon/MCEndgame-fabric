package de.fuballer.mcendgame.main.mixin.damage;

import de.fuballer.mcendgame.main.accessor.LivingEntityDamageAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDamageAccessorMixin implements LivingEntityDamageAccessor {
    @Shadow
    private DamageSource lastDamageSource;
    @Shadow
    private long lastDamageTime;

    @Shadow
    protected abstract void playThornsSound(DamageSource damageSource);

    @Shadow
    protected abstract boolean tryUseDeathProtector(DamageSource source);

    @Override
    public void mcendgame$setLastDamageSource(DamageSource damageSource) {
        this.lastDamageSource = damageSource;
    }

    @Override
    public void mcendgame$setLastDamageTime(long time) {
        this.lastDamageTime = time;
    }

    @Override
    public void mcendgame$playThornsSound(DamageSource damageSource) {
        this.playThornsSound(damageSource);
    }

    @Override
    public boolean mcendgame$tryUseDeathProtector(DamageSource source) {
        return this.tryUseDeathProtector(source);
    }
}
