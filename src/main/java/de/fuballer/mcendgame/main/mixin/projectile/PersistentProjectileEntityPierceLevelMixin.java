package de.fuballer.mcendgame.main.mixin.projectile;

import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityPierceLevelAccessor;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityPierceLevelMixin implements PersistentProjectileEntityPierceLevelAccessor {
    @Invoker("setPierceLevel")
    public abstract void mcendgame$callSetPierceLevel(byte level);
}
