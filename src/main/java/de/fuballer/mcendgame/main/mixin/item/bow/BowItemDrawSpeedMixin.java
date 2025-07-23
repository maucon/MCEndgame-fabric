package de.fuballer.mcendgame.main.mixin.item.bow;

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public class BowItemDrawSpeedMixin {
    @Redirect(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"))
    float getPullProgress(int useTicks, ItemStack stack, World world, LivingEntity user) {
        var attributes = CustomAttributesExtensions.INSTANCE.getAllCustomAttributes(user).get(CustomAttributeTypes.INSTANCE.getBOW_PULL_TICKS());
        var sum = attributes == null ? 0 : attributes.stream()
                .mapToInt(it -> CustomAttributesExtensions.INSTANCE.asIntRoll(it.getRolls().getFirst()).getActualRoll())
                .sum();

        var maxPullTicks = 20 + sum;
        return getPullProgress(useTicks, maxPullTicks);
    }

    @Unique
    private float getPullProgress(int useTicks, int maxPullTicks) {
        float percentage = useTicks / (float) maxPullTicks;
        var progress = (percentage * percentage + percentage * 2.0F) / 3.0F;
        return Math.min(progress, 1F);
    }
}
