package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.accessor.LivingEntityDelayedRemoveAttributeModifierAccessor
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDodgedEvent
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil.defaultJava
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import kotlin.math.abs

@Injectable
class IncreasedMovementSpeedAfterDodgingService {
    private val attributeModifierIdentifier: Identifier = defaultJava("increased_movement_speed_after_dodging")

    @EventSubscriber
    fun on(event: LivingEntityDodgedEvent) {
        val entity = event.entity
        val attributeInstance = entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED) ?: return

        val customAttributes = entity.getAllCustomAttributes()[CustomAttributeTypes.INCREASED_MOVEMENT_SPEED_AFTER_DODGING] ?: return
        val movementSpeed = customAttributes.sumOf { it.rolls[0].asDoubleRoll().getActualRoll() }
        val duration = customAttributes.maxOf { it.rolls[1].asIntRoll().getActualRoll() } * 20

        val accessor = entity as LivingEntityDelayedRemoveAttributeModifierAccessor
        accessor.`mcendgame$addAttributeModifierToRemove`(EntityAttributes.MOVEMENT_SPEED, attributeModifierIdentifier, duration)

        val existingModifier = attributeInstance.getModifier(attributeModifierIdentifier)
        if (existingModifier != null && abs(existingModifier.value() - movementSpeed) < 0.001) return

        val modifier = EntityAttributeModifier(
            attributeModifierIdentifier,
            movementSpeed,
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        )

        attributeInstance.removeModifier(attributeModifierIdentifier)
        attributeInstance.addTemporaryModifier(modifier)
    }
}