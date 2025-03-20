package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationEvent
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable

@Injectable
class IncreasedElementalDamageService {
    @Initialize
    fun on() = ApplyDamageCalculationEvent.NOTIFIER.listen { event ->
        val attributes = event.damagerAttributes[CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE] ?: return@listen

        attributes.forEach { attribute ->
            val increasedElementalDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            event.increasedElementalDamage.add(increasedElementalDamage)
        }
    }
}