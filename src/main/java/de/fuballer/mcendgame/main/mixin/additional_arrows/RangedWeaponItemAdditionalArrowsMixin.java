package de.fuballer.mcendgame.main.mixin.additional_arrows;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions;
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntRoll;
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(RangedWeaponItem.class)
public class RangedWeaponItemAdditionalArrowsMixin {
    @ModifyVariable(
            method = "load",
            at = @At(value = "STORE"),
            ordinal = 0
    )
    private static int modifyProjectileCount(
            int original,
            ItemStack stack,
            ItemStack projectileStack,
            LivingEntity shooter
    ) {
        var additional = getAdditionalArrowsFromAttribute(shooter);
        return original + additional;
    }

    @ModifyExpressionValue(
            method = "shootAll",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getProjectileSpread(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/Entity;F)F"
            )
    )
    private float modifySpread(
            float original,
            ServerWorld world,
            LivingEntity shooter
    ) {
        var additional = getAdditionalArrowsFromAttribute(shooter);
        return original + 5F * additional;
    }

    @Unique
    private static int getAdditionalArrowsFromAttribute(LivingEntity shooter) {
        var allAttributes = CustomAttributesExtensions.INSTANCE.getAllCustomAttributes(shooter);
        var attributes = allAttributes.get(CustomAttributeTypes.INSTANCE.getADDITIONAL_ARROWS());
        if (attributes == null || attributes.isEmpty()) return 0;

        var arrowCount = attributes.stream()
                .mapToInt(attr -> ((IntRoll) attr.getRolls().getFirst()).getValue())
                .sum();
        return Math.max(0, arrowCount);
    }
}
