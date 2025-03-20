package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationEvent
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable
import kotlin.random.Random

@Injectable
class DodgeService {
    @Initialize
    fun on() = ApplyDamageCalculationEvent.NOTIFIER.listen { event ->
        val attributes = event.damagedAttributes[CustomAttributeTypes.DODGE] ?: return@listen

        for (attribute in attributes) {
            val dodge = attribute.rolls[0].asDoubleRoll().getActualRoll()
            if (Random.nextDouble() > dodge) continue

            event.dodge = true
            return@listen
        }
    }
}