package de.fuballer.mcendgame.main.mixin.projectile;

import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityIsAdditionalAccessor;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityIsAdditionalMixin implements PersistentProjectileEntityIsAdditionalAccessor {
    @Unique
    private static final String LOAD_PROCESSED_NBT = "load_processed";
    @Unique
    private static final String IS_ADDITIONAL = "is_additional";

    @Unique
    private boolean loadProcessed = false;
    @Unique
    private boolean isAdditional = false;

    @Override
    public void mcendgame$setLoadProcessed(Boolean processed) {
        loadProcessed = processed;
    }

    @Override
    public boolean mcendgame$hasLoadBeenProcessed() {
        return loadProcessed;
    }

    @Override
    public void mcendgame$setIsAdditional(Boolean isAdditional) {
        this.isAdditional = isAdditional;
    }

    @Override
    public boolean mcendgame$isAdditional() {
        return isAdditional;
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeNBT(WriteView view, CallbackInfo ci) {
        if (loadProcessed) view.putBoolean(LOAD_PROCESSED_NBT, true);
        if (isAdditional) view.putBoolean(IS_ADDITIONAL, true);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readNBT(ReadView view, CallbackInfo ci) {
        loadProcessed = view.getBoolean(LOAD_PROCESSED_NBT, false);
        isAdditional = view.getBoolean(IS_ADDITIONAL, false);
    }
}
