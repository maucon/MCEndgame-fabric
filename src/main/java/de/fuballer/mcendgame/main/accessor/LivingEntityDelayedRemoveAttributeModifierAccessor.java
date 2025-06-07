package de.fuballer.mcendgame.main.accessor;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public interface LivingEntityDelayedRemoveAttributeModifierAccessor {
    void mcendgame$addAttributeModifierToRemove(RegistryEntry<EntityAttribute> type, Identifier identifier, int ticks);
}
