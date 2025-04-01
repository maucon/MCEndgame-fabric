package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationCommand
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