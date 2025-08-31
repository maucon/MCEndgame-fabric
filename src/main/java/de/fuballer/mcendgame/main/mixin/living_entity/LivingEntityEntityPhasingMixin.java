package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityEntityPhasingMixin {
    @Inject(
            method = "isPushable",
            at = @At("HEAD"),
            cancellable = true)
    void isPushable(CallbackInfoReturnable<Boolean> cir) {
        if (iSEntityPhasing()) cir.setReturnValue(false);
    }

    @Inject(
            method = "tickCramming",
            at = @At(value = "HEAD"),
            cancellable = true)
    void tickCramming(CallbackInfo ci) {
        if (iSEntityPhasing()) ci.cancel();
    }

    @Unique
    private boolean iSEntityPhasing() {
        var livingEntity = (LivingEntity) (Object) this;
        return CustomAttributesExtensions.INSTANCE.isEntityPhasing(livingEntity);
    }
}
