package de.fuballer.mcendgame.main.mixin.item;

import de.fuballer.mcendgame.main.messaging.misc.ItemEntityDamagedCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ImmuneItemEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        var entity = (ItemEntity) (Object) this;
        var itemEntityDamagedCommand = ItemEntityDamagedCommand.Companion.of(entity, source);
        var cmd = CommandGateway.INSTANCE.apply(itemEntityDamagedCommand);

        if (!cmd.getIgnoresDamage()) return;

        cir.setReturnValue(false);
    }
}
