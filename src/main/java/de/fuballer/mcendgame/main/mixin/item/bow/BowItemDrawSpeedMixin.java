package de.fuballer.mcendgame.main.mixin.item.bow;

import net.minecraft.item.BowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class BowItemDrawSpeedMixin {
   /* @Inject(method = "getPullProgress", at = @At("HEAD"), cancellable = true)
    private static void getPullProgress(int useTicks, CallbackInfoReturnable<Float> cir) {
        float f = useTicks / 20F;
        f = (f * f + f * 2F) / 3F * 2F;
        if (f > 1F) {
            f = 1F;
        }

        cir.setReturnValue(f);
    }*/
}
