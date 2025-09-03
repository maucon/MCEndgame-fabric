package de.fuballer.mcendgame.client.mixin.renderer;

import de.fuballer.mcendgame.main.MCEndgame;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackAttributeRenderMixin {
    @Shadow
    protected abstract void appendAttributeModifierTooltip(Consumer<Text> textConsumer, @Nullable PlayerEntity player, RegistryEntry<EntityAttribute> attribute, EntityAttributeModifier modifier);

    @Inject(at = @At("HEAD"), method = "appendAttributeModifiersTooltip", cancellable = true) // TODO make less invasive
    protected void appendAttributeModifiersTooltip(
            Consumer<Text> textConsumer,
            TooltipDisplayComponent displayComponent,
            PlayerEntity player,
            CallbackInfo ci
    ) {
        var itemStack = (ItemStack) (Object) this;

        if (displayComponent.shouldDisplay(DataComponentTypes.ATTRIBUTE_MODIFIERS)) {
            AttributeModifierSlot[] var4 = AttributeModifierSlot.values();

            for (AttributeModifierSlot attributeModifierSlot : var4) {
                MutableBoolean mutableBoolean = new MutableBoolean(true);
                itemStack.applyAttributeModifier(attributeModifierSlot, (attribute, modifier) -> {
                    if (!modifier.id().getNamespace().equals(MCEndgame.MOD_ID)) { // added line
                        if (mutableBoolean.isTrue()) {
                            textConsumer.accept(ScreenTexts.EMPTY);
                            textConsumer.accept(Text.translatable("item.modifiers." + attributeModifierSlot.asString()).formatted(Formatting.GRAY));
                            mutableBoolean.setFalse();
                        }

                        this.appendAttributeModifierTooltip(textConsumer, player, attribute, modifier);
                    } // added line
                });
            }

        }

        ci.cancel();
    }
}
