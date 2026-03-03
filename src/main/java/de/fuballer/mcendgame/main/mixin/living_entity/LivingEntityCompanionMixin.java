package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.accessor.LivingEntityCompanionAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityCompanionMixin implements LivingEntityCompanionAccessor {
    @Unique
    private static final String COMPANION_NBT = "isCompanion";
    @Unique
    private boolean isCompanion = false;

    @Override
    public boolean mcendgame$isCompanion() {
        return isCompanion;
    }

    @Override
    public void mcendgame$setCompanion() {
        isCompanion = true;
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeNBT(WriteView view, CallbackInfo ci) {
        if (isCompanion) view.putBoolean(COMPANION_NBT, true);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readNBT(ReadView view, CallbackInfo ci) {
        isCompanion = view.getBoolean(COMPANION_NBT, false);
    }
}
