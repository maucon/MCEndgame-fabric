package de.fuballer.mcendgame.main.util.extension.mixin

import de.fuballer.mcendgame.main.accessor.WolfEntityColorAndVariantAccessor
import net.minecraft.entity.passive.WolfEntity
import net.minecraft.entity.passive.WolfVariant
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.DyeColor

object WolfMixinExtension {
    fun WolfEntity.setVariant(variant: RegistryEntry<WolfVariant>) {
        val accessor = this as WolfEntityColorAndVariantAccessor
        accessor.`mcendgame$callSetVariant`(variant)
    }

    fun WolfEntity.setCollarColor(color: DyeColor) {
        val accessor = this as WolfEntityColorAndVariantAccessor
        accessor.`mcendgame$callSetCollarColor`(color)
    }
}