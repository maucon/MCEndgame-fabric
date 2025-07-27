package de.fuballer.mcendgame.main.mixin.skeleton;

import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityBowMixin {
    @Redirect(
            method = "updateAttackType",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            )
    )
    private boolean redirectIsOf(ItemStack instance, Item item) {
        var entity = (AbstractSkeletonEntity) (Object) this;
        var hand = entity.getMainHandStack().getItem() instanceof BowItem ? Hand.MAIN_HAND : Hand.OFF_HAND;
        var stack = entity.getStackInHand(hand);
        return stack.getItem() instanceof BowItem;
    }

    @Redirect(
            method = "shootAt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/ProjectileUtil;getHandPossiblyHolding(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/Item;)Lnet/minecraft/util/Hand;")
    )
    Hand redirectHandPossiblyHolding(LivingEntity entity, Item item) {
        return entity.getMainHandStack().getItem() instanceof BowItem ? Hand.MAIN_HAND : Hand.OFF_HAND;
    }

    @Inject(method = "getRegularAttackInterval", at = @At("HEAD"), cancellable = true)
    void getRegularAttackInterval(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(40 + getAdditionalAttackIntervalTicks() * 2);
    }

    @Inject(method = "getHardAttackInterval", at = @At("HEAD"), cancellable = true)
    void getHardAttackInterval(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(20 + getAdditionalAttackIntervalTicks());
    }

    @Unique
    private int getAdditionalAttackIntervalTicks() {
        var entity = (AbstractSkeletonEntity) (Object) this;
        return EntityExtension.INSTANCE.getAdditionalBowPullTicks(entity);
    }
}
