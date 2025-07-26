package de.fuballer.mcendgame.main.mixin.projectile;

import de.fuballer.mcendgame.main.accessor.PersistentProjectileEntityIsAdditionalAccessor;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.nbt.NbtCompound;
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


    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeNBT(NbtCompound nbt, CallbackInfo ci) {
        if (loadProcessed) nbt.putBoolean(LOAD_PROCESSED_NBT, true);
        if (isAdditional) nbt.putBoolean(IS_ADDITIONAL, true);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readNBT(NbtCompound nbt, CallbackInfo ci) {
        loadProcessed = nbt.getBoolean(LOAD_PROCESSED_NBT).orElse(false);
        isAdditional = nbt.getBoolean(IS_ADDITIONAL).orElse(false);
    }
}
