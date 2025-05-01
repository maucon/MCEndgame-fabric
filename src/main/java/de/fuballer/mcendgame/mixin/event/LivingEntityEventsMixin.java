package de.fuballer.mcendgame.mixin.event;

import de.fuballer.mcendgame.messaging.LivingEntityDeathEvent;
import de.maucon.mauconframework.event.EventGateway;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEventsMixin {

    @Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updatePostDeath()V"))
    private void baseTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.isDead() && entity.deathTime == 0) {
            var event = new LivingEntityDeathEvent(entity);
            EventGateway.INSTANCE.launchPublish(event);
        }
    }
}
