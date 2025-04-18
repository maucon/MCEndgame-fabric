package de.fuballer.mcendgame.mixin;

import de.fuballer.mcendgame.components.item_filter.PlayerItemPickupCommand;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityFilterMixin {
    @Inject(method = "onPlayerCollision", at = @At("HEAD"), cancellable = true)
    void applyFilter(PlayerEntity player, CallbackInfo ci) {
        var itemEntity = (ItemEntity) (Object) this;
        var itemStack = itemEntity.getStack();
        var item = itemStack.getItem();

        var command = new PlayerItemPickupCommand(player, item);
        CommandGateway.INSTANCE.apply(command);

        if (command.isCancelled()) ci.cancel();
    }
}
