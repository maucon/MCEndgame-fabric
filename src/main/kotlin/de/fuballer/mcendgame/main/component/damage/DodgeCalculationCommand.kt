package de.fuballer.mcendgame.main.component.damage

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttributeType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageType
import net.minecraft.entity.projectile.PersistentProjectileEntity

data class DodgeCalculationCommand(
    val damaged: LivingEntity,
    val damagedAttributes: Map<CustomAttributeType, List<CustomAttribute>>,
    val type: DamageType,
    val isProjectile: Boolean,
    var isDodging: Boolean = false,
) {
    companion object {
        fun of(
            damaged: LivingEntity,
            source: DamageSource,
        ) = DodgeCalculationCommand(damaged, damaged.getAllCustomAttributes(), source.type, source.source is PersistentProjectileEntity)
    }
}