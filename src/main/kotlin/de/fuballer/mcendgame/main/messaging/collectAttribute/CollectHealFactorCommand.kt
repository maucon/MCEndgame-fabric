package de.fuballer.mcendgame.main.messaging.collectAttribute

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttributeType
import net.minecraft.entity.LivingEntity

data class CollectHealFactorCommand(
    val entity: LivingEntity,
    val attributes: Map<CustomAttributeType, List<CustomAttribute>> = entity.getAllCustomAttributes(),
    val increased: MutableList<Double> = mutableListOf(),
    val more: MutableList<Double> = mutableListOf(),
) {
    companion object {
        fun forEntity(entity: LivingEntity) = CollectHealFactorCommand(entity)
    }

    fun getFactor() = (1 + increased.sum()) * more.fold(1.0) { a, b -> a * (1 + b) }
}