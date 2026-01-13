package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDeathEvent
import de.fuballer.mcendgame.main.util.extension.mixin.EntityMixinExtension.addTemporaryAttributeModifier
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil.defaultJava
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier

@Injectable
class IncreasedMovementSpeedOnKillService {
    private val attributeModifierIdentifier: Identifier = defaultJava("increased_movement_speed_on_kill")

    @EventSubscriber
    fun on(event: LivingEntityDeathEvent) {
        val killer = event.killer ?: return

        val customAttributes = killer.getAllCustomAttributes()[CustomAttributeTypes.INCREASED_MOVEMENT_SPEED_ON_KILL] ?: return

        // TODO #180 add each attribute separate (requires attributes to have ids)
        val movementSpeed = customAttributes.sumOf { it.rolls[0].asDoubleRoll().getValue() }
        val duration = customAttributes.maxOf { it.rolls[1].asIntRoll().getValue() } * 20

        killer.addTemporaryAttributeModifier(
            EntityAttributes.MOVEMENT_SPEED,
            attributeModifierIdentifier,
            duration,
            movementSpeed,
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        )
    }
}