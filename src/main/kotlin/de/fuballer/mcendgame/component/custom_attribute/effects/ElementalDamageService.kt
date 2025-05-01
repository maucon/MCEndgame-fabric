package de.fuballer.mcendgame.component.custom_attribute.effects

import de.fuballer.mcendgame.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.component.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class ElementalDamageService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val attributes = cmd.damagerAttributes[CustomAttributeTypes.ELEMENTAL_DAMAGE] ?: return

        attributes.forEach { attribute ->
            val elementalDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.elementalDamage.add(elementalDamage)
        }
    }
}