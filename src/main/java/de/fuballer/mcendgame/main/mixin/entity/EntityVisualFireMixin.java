package de.fuballer.mcendgame.main.mixin.entity;

import de.fuballer.mcendgame.main.accessor.LivingEntityVisualFireAccessor;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityVisualFireMixin {
    @Inject(method = "doesRenderOnFire", at = @At("HEAD"), cancellable = true)
    void doesRenderOnFire(CallbackInfoReturnable<Boolean> cir) {
        if (!(this instanceof LivingEntityVisualFireAccessor accessor)) return;
        if (!accessor.mcendgame$hasVisualFire()) return;
        cir.setReturnValue(true);
    }
}