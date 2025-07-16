package de.fuballer.mcendgame.main.util.extension.mixin

import de.fuballer.mcendgame.main.accessor.LivingEntityDodgedRecentlyAccessor
import de.fuballer.mcendgame.main.accessor.LivingEntityTemporaryAttributeModifierAccessor
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier

object LivingEntityMixinExtension {
    fun LivingEntity.addTemporaryAttributeModifier(
        type: RegistryEntry<EntityAttribute?>?,
        identifier: Identifier?,
        ticks: Int,
        value: Double,
        operation: EntityAttributeModifier.Operation?
    ) {
        val accessor = this as LivingEntityTemporaryAttributeModifierAccessor
        accessor.`mcendgame$addTemporaryAttributeModifier`(type, identifier, ticks, value, operation)
    }

    fun LivingEntity.updateDodged() {
        val accessor = this as LivingEntityDodgedRecentlyAccessor
        return accessor.`mcendgame$updateDodge`()
    }

    fun LivingEntity.hasDodged(ticks: Int): Boolean {
        val accessor = this as LivingEntityDodgedRecentlyAccessor
        return accessor.`mcendgame$hasDodged`(ticks)
    }
}
