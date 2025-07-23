package de.fuballer.mcendgame.main.mixin.item.bow;

import de.fuballer.mcendgame.main.component.custom_attribute.effects.BowPullUtil;
import de.fuballer.mcendgame.main.util.extension.EntityExtension;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public class BowItemDrawSpeedMixin {
    @Redirect(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"))
    float getPullProgress(int useTicks, ItemStack stack, World world, LivingEntity user) {
        var fullPullTicks = EntityExtension.INSTANCE.getBowFullPullTicks(user);
        return BowPullUtil.INSTANCE.getPullProgress(useTicks, fullPullTicks);
    }
}
