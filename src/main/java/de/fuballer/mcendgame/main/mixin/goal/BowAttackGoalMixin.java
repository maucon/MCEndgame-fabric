package de.fuballer.mcendgame.main.mixin.goal;

import de.fuballer.mcendgame.main.component.custom_attribute.effects.BowPullUtil;
import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowAttackGoal.class)
public class BowAttackGoalMixin {
    @Inject(method = "isHoldingBow", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;isHolding(Lnet/minecraft/item/Item;)Z"), cancellable = true)
    void isHoldingBow(CallbackInfoReturnable<Boolean> cir) {
        var stack = accessActor().getMainHandStack();
        cir.setReturnValue(stack.getItem() instanceof BowItem);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;setCurrentHand(Lnet/minecraft/util/Hand;)V"))
    void redirectSetCurrentHand(HostileEntity instance, Hand hand) {
        var actor = accessActor();
        var newHand = actor.getMainHandStack().getItem() instanceof BowItem ? Hand.MAIN_HAND : Hand.OFF_HAND;
        actor.setCurrentHand(newHand);
    }

    @ModifyConstant(method = "tick", constant = @Constant(intValue = 20))
    int modifyBowPullTickThreshold(int constant) {
        var additionalTicks = EntityExtension.INSTANCE.getAdditionalBowPullTicks(accessActor());
        return constant + additionalTicks;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"))
    float getPullProgress(int useTicks) {
        var fullPullTicks = EntityExtension.INSTANCE.getBowFullPullTicks(accessActor());
        return BowPullUtil.INSTANCE.getPullProgress(useTicks, fullPullTicks);
    }

    @Unique
    private HostileEntity accessActor() {
        var accessor = (BowAttackGoalAccessor) this;
        return accessor.getActor();
    }
}