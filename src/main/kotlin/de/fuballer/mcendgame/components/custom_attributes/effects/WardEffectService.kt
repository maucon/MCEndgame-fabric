package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class WardEffectService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val attributes = cmd.damagedAttributes[CustomAttributeTypes.WARD] ?: return

        attributes.forEach { attribute ->
            val ward = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.ward.add(ward)
        }
    }
}