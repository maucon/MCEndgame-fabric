package de.fuballer.mcendgame.main.accessor;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public interface LivingEntityTemporaryAttributeModifierAccessor {
    void mcendgame$addTemporaryAttributeModifier(
            RegistryEntry<EntityAttribute> type,
            Identifier identifier,
            int ticks,
            double value,
            EntityAttributeModifier.Operation operation
    );
}
