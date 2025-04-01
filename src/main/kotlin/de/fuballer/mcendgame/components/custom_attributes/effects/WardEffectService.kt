package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationEvent
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class WardEffectService {
    @Initializer
    fun on() = ApplyDamageCalculationEvent.NOTIFIER.listen { event ->
        val attributes = event.damagedAttributes[CustomAttributeTypes.WARD] ?: return@listen

        attributes.forEach { attribute ->
            val ward = attribute.rolls[0].asDoubleRoll().getActualRoll()
            event.ward.add(ward)
        }
    }
}