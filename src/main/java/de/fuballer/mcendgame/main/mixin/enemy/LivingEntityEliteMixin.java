package de.fuballer.mcendgame.main.mixin.enemy;

import de.fuballer.mcendgame.main.accessor.LivingEntityEliteAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityEliteMixin implements LivingEntityEliteAccessor {
    @Unique
    private static final String ELITE_NBT = "isElite";
    @Unique
    private boolean isElite = false;

    @Override
    public boolean mcendgame$isElite() {
        return isElite;
    }

    @Override
    public void mcendgame$setElite(boolean isElite) {
        this.isElite = isElite;
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeNBT(WriteView view, CallbackInfo ci) {
        if (!isElite) return;
        view.putBoolean(ELITE_NBT, true);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readNBT(ReadView view, CallbackInfo ci) {
        isElite = view.getBoolean(ELITE_NBT, false);
    }
}
