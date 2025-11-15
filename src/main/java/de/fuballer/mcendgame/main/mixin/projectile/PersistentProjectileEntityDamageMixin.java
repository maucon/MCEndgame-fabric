package de.fuballer.mcendgame.main.mixin.projectile;

import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityDamageAccessor;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityDamageMixin implements PersistentProjectileEntityDamageAccessor {
    @Shadow
    private double damage;

    @Override
    public double mcendgame$getDamage() {
        return this.damage;
    }
}
