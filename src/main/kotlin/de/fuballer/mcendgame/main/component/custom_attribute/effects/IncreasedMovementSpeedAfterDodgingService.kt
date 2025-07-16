package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.util.extension.mixin.LivingEntityMixinExtension.addTemporaryAttributeModifier
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDodgedEvent
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil.defaultJava
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier

@Injectable
class IncreasedMovementSpeedAfterDodgingService {
    private val attributeModifierIdentifier: Identifier = defaultJava("increased_movement_speed_after_dodging")

    @EventSubscriber
    fun on(event: LivingEntityDodgedEvent) {
        val entity = event.entity

        val customAttributes = entity.getAllCustomAttributes()[CustomAttributeTypes.INCREASED_MOVEMENT_SPEED_AFTER_DODGING] ?: return
        val movementSpeed = customAttributes.sumOf { it.rolls[0].asDoubleRoll().getActualRoll() }
        val duration = customAttributes.maxOf { it.rolls[1].asIntRoll().getActualRoll() } * 20

        entity.addTemporaryAttributeModifier(
            EntityAttributes.MOVEMENT_SPEED,
            attributeModifierIdentifier,
            duration,
            movementSpeed,
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        )
    }
}