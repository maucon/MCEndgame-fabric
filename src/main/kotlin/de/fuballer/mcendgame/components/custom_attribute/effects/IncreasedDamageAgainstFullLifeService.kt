package de.fuballer.mcendgame.components.custom_attribute.effects

import de.fuballer.mcendgame.components.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class IncreasedDamageAgainstFullLifeService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val attributes = cmd.damagerAttributes[CustomAttributeTypes.INCREASED_DAMAGE_AGAINST_FULL_LIFE] ?: return
        if (cmd.damaged.health < cmd.damaged.maxHealth) return

        attributes.forEach { attribute ->
            val increasedDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.increasedDamage.add(increasedDamage)
        }
    }
}