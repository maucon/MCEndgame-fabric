package de.fuballer.mcendgame.main.messaging.collectAttribute

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttributeType
import net.minecraft.entity.LivingEntity

data class CollectHealPowerCommand(
    override val entity: LivingEntity,
    override val attributes: Map<CustomAttributeType, List<CustomAttribute>> = entity.getAllCustomAttributes(),
    override val flat: MutableList<Double> = mutableListOf(),
    override val increased: MutableList<Double> = mutableListOf(),
    override val more: MutableList<Double> = mutableListOf(),
) : CollectAttributeStatCommand