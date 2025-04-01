package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationEvent
import de.maucon.mauconframework.initializer.Initializer
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class IncreasedProjectileDamageService {
    @Initializer
    fun on() = ApplyDamageCalculationEvent.NOTIFIER.listen { event ->
        val attributes = event.damagerAttributes[CustomAttributeTypes.INCREASED_PROJECTILE_DAMAGE] ?: return@listen

        attributes.forEach { attribute ->
            val increaseProjectileDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            event.increasedProjectileDamage.add(increaseProjectileDamage)
        }
    }
}