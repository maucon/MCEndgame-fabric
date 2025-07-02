package de.fuballer.mcendgame.main.mixin.wolf_entity;

import de.fuballer.mcendgame.main.accessor.WolfEntityColorAndVariantAccessor;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WolfEntity.class)
public abstract class WolfEntityColorAndVariantMixin implements WolfEntityColorAndVariantAccessor {
    @Invoker("setVariant")
    public abstract void mcendgame$callSetVariant(RegistryEntry<WolfVariant> variant);

    @Invoker("setCollarColor")
    public abstract void mcendgame$callSetCollarColor(DyeColor color);
}
