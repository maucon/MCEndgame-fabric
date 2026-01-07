package de.fuballer.mcendgame.main.mixin.player;

import de.fuballer.mcendgame.main.messaging.misc.PlayerTakeShieldHitCommand;
import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import de.maucon.mauconframework.command.CommandGateway;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PlayerEntity.class)
public class PlayerEntityDisableShieldMixin {
    @ModifyVariable(
            method = "takeShieldHit",
            at = @At(value = "STORE"),
            ordinal = 0
    )
    private float modifyDisableBlockingTime(
            float originalCooldown
    ) {
        var player = (PlayerEntity) (Object) this;

        var itemStack = player.getBlockingItem();
        var component = itemStack != null ? itemStack.get(DataComponentTypes.BLOCKS_ATTACKS) : null;
        if (component == null) return originalCooldown;

        var command = new PlayerTakeShieldHitCommand(player, itemStack, originalCooldown);
        var cmd = CommandGateway.INSTANCE.apply(command);

        return cmd.getShieldCooldown();
    }

    @Inject(
            method = "takeShieldHit",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/component/type/BlocksAttacksComponent;applyShieldCooldown(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LivingEntity;FLnet/minecraft/item/ItemStack;)V"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true
    )
    void modifyShieldDisableSeconds(
            ServerWorld world,
            LivingEntity attacker,
            CallbackInfo ci,
            ItemStack itemStack,
            BlocksAttacksComponent blocksAttacksComponent,
            float cooldown
    ) {
        var playerEntity = (PlayerEntity) (Object) this;
        EntityExtension.INSTANCE.setShieldsCooldown(playerEntity, cooldown);
        ci.cancel();
    }
}
