package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationEvent
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable

@Injectable
class DamageAgainstFullLifeService {
    @Initialize
    fun on() = ApplyDamageCalculationEvent.NOTIFIER.listen { event ->
        val attributes = event.damagerAttributes[CustomAttributeTypes.DAMAGE_AGAINST_FULL_LIFE] ?: return@listen
        if (event.damaged.health < event.damaged.maxHealth) return@listen

        attributes.forEach { attribute ->
            val increaseDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            event.increasedDamage.add(increaseDamage)
        }
    }
}