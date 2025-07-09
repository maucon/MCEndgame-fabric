package de.fuballer.mcendgame.main.mixin.entity;

import de.fuballer.mcendgame.main.accessor.LivingEntityCompanionAccessor;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityCompanionPortalMixin {
    @Inject(method = "canUsePortals", at = @At("HEAD"), cancellable = true)
    void canUsePortals(boolean allowVehicles, CallbackInfoReturnable<Boolean> cir) {
        var entity = (Entity) (Object) this;
        if (!(entity instanceof LivingEntityCompanionAccessor accessor)) return;
        if (!accessor.mcendgame$isCompanion()) return;
        cir.setReturnValue(false);
    }
}
