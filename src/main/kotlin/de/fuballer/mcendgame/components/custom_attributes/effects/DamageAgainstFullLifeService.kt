package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.data.DoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationEvent
import de.maucon.mauconframework.annotation.Initialize
import de.maucon.mauconframework.annotation.Injectable

@Injectable
class DamageAgainstFullLifeService {
    @Initialize
    fun on() = ApplyDamageCalculationEvent.NOTIFIER.listen { event ->
        val attributes = event.damagerAttributes[CustomAttributeTypes.DAMAGE_AGAINST_FULL_LIFE] ?: return@listen

        println("hit me")
        if (event.damaged.health < event.damaged.maxHealth) return@listen
        println("big damage")

        attributes.forEach { attribute ->
            val roll = attribute.rolls.first() as DoubleRoll
            val actualRoll = roll.getActualRoll()
            println("actual roll: $actualRoll")
            event.increasedDamage.add(actualRoll)
        }
    }
}