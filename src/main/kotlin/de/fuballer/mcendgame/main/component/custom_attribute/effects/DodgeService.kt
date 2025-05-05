package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
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