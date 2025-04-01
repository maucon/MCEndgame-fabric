package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationEvent
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class LessDamageTakenService {
    @Initializer
    fun on() = ApplyDamageCalculationEvent.NOTIFIER.listen { event ->
        val attributes = event.damagedAttributes[CustomAttributeTypes.LESS_DAMAGE_TAKEN] ?: return@listen

        attributes.forEach { attribute ->
            val lessDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            event.lessDamage.add(lessDamage)
        }
    }
}