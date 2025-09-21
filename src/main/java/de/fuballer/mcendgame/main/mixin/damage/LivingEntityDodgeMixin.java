package de.fuballer.mcendgame.main.mixin.damage;

import de.fuballer.mcendgame.main.component.custom_attribute.effects.dodge.DodgeSettings;
import de.fuballer.mcendgame.main.component.damage.DodgeCalculationCommand;
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDodgedEvent;
import de.maucon.mauconframework.command.CommandGateway;
import de.maucon.mauconframework.event.EventGateway;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityDodgeMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true, order = 950)
    public void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        var entity = (LivingEntity) (Object) this;

        var key = source.getTypeRegistryEntry().getKey();
        if (key.isEmpty()) return;
        if (!DodgeSettings.INSTANCE.getDODGEABLE_DAMAGE_TYPES().contains(key.get())) return;

        var dodgeCalculationCommand = DodgeCalculationCommand.Companion.of(entity, source);
        var cmd = CommandGateway.INSTANCE.apply(dodgeCalculationCommand);
        if (!cmd.isDodging()) return;

        var dodgeEvent = new LivingEntityDodgedEvent(entity, source.getAttacker());
        EventGateway.INSTANCE.launchPublish(dodgeEvent);

        cir.setReturnValue(false);
    }
}
