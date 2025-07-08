package de.fuballer.mcendgame.main.accessor;

import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;

public interface WolfEntityColorAndVariantAccessor {
    void mcendgame$callSetVariant(RegistryEntry<WolfVariant> variant);

    void mcendgame$callSetCollarColor(DyeColor color);
}
