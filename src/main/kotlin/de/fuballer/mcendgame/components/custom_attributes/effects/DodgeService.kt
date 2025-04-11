package de.fuballer.mcendgame.components.custom_attributes.effects

import de.fuballer.mcendgame.components.custom_attributes.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import kotlin.random.Random

@Injectable
class DodgeService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val attributes = cmd.damagedAttributes[CustomAttributeTypes.DODGE] ?: return

        for (attribute in attributes) {
            val dodge = attribute.rolls[0].asDoubleRoll().getActualRoll()
            if (Random.nextDouble() > dodge) continue

            cmd.dodge = true
            return
        }
    }
}