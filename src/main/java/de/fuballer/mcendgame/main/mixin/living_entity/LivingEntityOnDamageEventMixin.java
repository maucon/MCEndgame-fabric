package de.fuballer.mcendgame.main.mixin.living_entity;

import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamagedEvent;
import de.maucon.mauconframework.event.EventGateway;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityOnDamageEventMixin {
    @Inject(method = "damage", at = @At("RETURN"))
    void damage(
            ServerWorld world,
            DamageSource source,
            float amount,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!cir.getReturnValue()) return;

        var entity = (LivingEntity) (Object) this;
        var event = new LivingEntityDamagedEvent(entity, source, amount);
        EventGateway.INSTANCE.launchPublish(event);
    }
}
