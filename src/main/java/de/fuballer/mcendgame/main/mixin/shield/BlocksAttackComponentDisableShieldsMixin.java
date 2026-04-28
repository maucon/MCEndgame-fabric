package de.fuballer.mcendgame.main.mixin.shield;

import de.fuballer.mcendgame.main.messaging.misc.ShieldHitEvent;
import de.maucon.mauconframework.event.EventGateway;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlocksAttacksComponent.class)
public class BlocksAttackComponentDisableShieldsMixin {
    @Inject(
            method = "onShieldHit",
            at = @At("HEAD")
    )
    void disableShields(
            World world,
            ItemStack stack,
            LivingEntity entity,
            Hand hand,
            float itemDamage,
            CallbackInfo ci
    ) {
        var event = new ShieldHitEvent(entity, stack);
        EventGateway.INSTANCE.publish(event);
    }
}
