package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class LessDamageTakenService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val attributes = cmd.damagedAttributes[CustomAttributeTypes.LESS_DAMAGE_TAKEN] ?: return

        attributes.forEach { attribute ->
            val lessDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.lessDamage.add(lessDamage)
        }
    }
}