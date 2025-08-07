package de.fuballer.mcendgame.main.mixin.entity;

import de.fuballer.mcendgame.main.accessor.EntityForcedGlowColorAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityForcedGlowColorMixin implements EntityForcedGlowColorAccessor {
    @Shadow
    @Final
    protected DataTracker dataTracker;
    @Unique
    private static final TrackedData<Integer> FORCED_GLOW_COLOR = DataTracker.registerData(Entity.class, TrackedDataHandlerRegistry.INTEGER);

    @ModifyVariable(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;initDataTracker(Lnet/minecraft/entity/data/DataTracker$Builder;)V", shift = At.Shift.AFTER)
    )
    private DataTracker.Builder modifyBuilder(DataTracker.Builder builder) {
        builder.add(FORCED_GLOW_COLOR, -1);
        return builder;
    }

    @Inject(method = "getTeamColorValue", at = @At("HEAD"), cancellable = true)
    void getTeamColorValue(CallbackInfoReturnable<Integer> cir) {
        var forcedColor = dataTracker.get(FORCED_GLOW_COLOR);
        if (forcedColor == -1) return;
        cir.setReturnValue(forcedColor);
    }

    @Override
    public void mcendgame$setForcedGlowColor(int color) {
        dataTracker.set(FORCED_GLOW_COLOR, color);
    }
}