package de.fuballer.mcendgame.main.mixin.entity;

import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityCompanionPortalMixin {
    @Inject(method = "canUsePortals", at = @At("HEAD"), cancellable = true)
    void canUsePortals(boolean allowVehicles, CallbackInfoReturnable<Boolean> cir) {
        var entity = (Entity) (Object) this;
        if (!(entity instanceof LivingEntity)) return;
        if (!EntityMixinExtension.INSTANCE.isCompanion((LivingEntity) entity)) return;

        cir.setReturnValue(false);
    }
}
