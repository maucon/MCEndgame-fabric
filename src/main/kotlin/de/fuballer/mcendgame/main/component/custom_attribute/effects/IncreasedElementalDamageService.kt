package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class IncreasedElementalDamageService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val attributes = cmd.damagerAttributes[CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE] ?: return

        attributes.forEach { attribute ->
            val increasedElementalDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.increasedElementalDamage.add(increasedElementalDamage)
        }
    }
}