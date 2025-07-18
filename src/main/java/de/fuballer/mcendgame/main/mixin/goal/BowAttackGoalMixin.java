package de.fuballer.mcendgame.main.mixin.goal;

import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowAttackGoal.class)
public class BowAttackGoalMixin {
    @Inject(method = "isHoldingBow", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;isHolding(Lnet/minecraft/item/Item;)Z"), cancellable = true)
    void isHoldingBow(CallbackInfoReturnable<Boolean> cir) {
        var accessor = (BowAttackGoalAccessor) this;
        var stack = accessor.getActor().getMainHandStack();
        cir.setReturnValue(stack.getItem() instanceof BowItem);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;setCurrentHand(Lnet/minecraft/util/Hand;)V"))
    void redirectSetCurrentHand(HostileEntity instance, Hand hand) {
        var accessor = (BowAttackGoalAccessor) this;
        var actor = accessor.getActor();
        var newHand = actor.getMainHandStack().getItem() instanceof BowItem ? Hand.MAIN_HAND : Hand.OFF_HAND;
        actor.setCurrentHand(newHand);
    }
}
