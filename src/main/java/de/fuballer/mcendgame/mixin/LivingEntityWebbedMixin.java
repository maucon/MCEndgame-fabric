package de.fuballer.mcendgame.mixin;

import de.fuballer.mcendgame.mixin_interfaces.LivingEntityWebbedAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityWebbedMixin implements LivingEntityWebbedAccessor {
    @Unique
    private static final TrackedData<Boolean> IS_WEBBED =
            DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(IS_WEBBED, false);
    }

    @Override
    public void mcendgame$setWebbed(boolean webbed) {
        LivingEntity entity = (LivingEntity) (Object) this;
        entity.getDataTracker().set(IS_WEBBED, webbed);
    }

    @Override
    public boolean mcendgame$isWebbed() {
        LivingEntity entity = (LivingEntity) (Object) this;
        return entity.getDataTracker().get(IS_WEBBED);
    }
}
