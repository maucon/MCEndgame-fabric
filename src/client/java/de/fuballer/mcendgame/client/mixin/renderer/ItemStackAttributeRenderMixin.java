package de.fuballer.mcendgame.client.mixin.renderer;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.fuballer.mcendgame.main.MCEndgame;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import org.apache.commons.lang3.function.TriConsumer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackAttributeRenderMixin {
    @WrapOperation(
            method = "appendAttributeModifiersTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;applyAttributeModifier(Lnet/minecraft/component/type/AttributeModifierSlot;Lorg/apache/commons/lang3/function/TriConsumer;)V"
            )
    )
    private void filterCustomModifiers(
            ItemStack instance,
            AttributeModifierSlot slot,
            TriConsumer<RegistryEntry<EntityAttribute>, EntityAttributeModifier, AttributeModifiersComponent.Display> attributeModifierConsumer,
            Operation<Void> original
    ) {
        TriConsumer<RegistryEntry<EntityAttribute>, EntityAttributeModifier, AttributeModifiersComponent.Display> wrappedConsumer
                = (attribute, modifier, display) -> {
            if (modifier.id().getNamespace().equals(MCEndgame.MOD_ID)) return;

            attributeModifierConsumer.accept(attribute, modifier, display);
        };
        original.call(instance, slot, wrappedConsumer);
    }
}
