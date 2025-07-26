package de.fuballer.mcendgame.main.mixin.projectile;

import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityIsAdditionalAccessor;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityIsAdditionalMixin implements PersistentProjectileEntityIsAdditionalAccessor {
    @Unique
    private boolean isAdditional = false;

    @Override
    public void mcendgame$setIsAdditional(Boolean isAdditional) {
        this.isAdditional = isAdditional;
    }

    @Override
    public boolean mcendgame$isAdditional() {
        return isAdditional;
    }
}
