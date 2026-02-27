package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.accessor.LivingEntityVisualFireAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityVisualFireMixin implements LivingEntityVisualFireAccessor {
    @Unique
    private static final String VISUAL_FIRE_NBT = "visualFire";

    @Unique
    private static final TrackedData<Boolean> VISUAL_FIRE = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(VISUAL_FIRE, false);
    }

    @Override
    public void mcendgame$setVisualFire(boolean fire) {
        LivingEntity entity = (LivingEntity) (Object) this;
        entity.getDataTracker().set(VISUAL_FIRE, fire);
    }

    @Override
    public boolean mcendgame$hasVisualFire() {
        LivingEntity entity = (LivingEntity) (Object) this;
        return entity.getDataTracker().get(VISUAL_FIRE);
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    void writeNBT(WriteView view, CallbackInfo ci) {
        if (mcendgame$hasVisualFire()) view.putBoolean(VISUAL_FIRE_NBT, true);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readNBT(ReadView view, CallbackInfo ci) {
        mcendgame$setVisualFire(view.getBoolean(VISUAL_FIRE_NBT, false));
    }
}
