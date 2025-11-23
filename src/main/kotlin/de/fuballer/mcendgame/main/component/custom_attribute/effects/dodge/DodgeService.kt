package de.fuballer.mcendgame.main.component.custom_attribute.effects.dodge

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.dodge.DodgeCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import kotlin.random.Random

@Injectable
class DodgeService {
    @CommandHandler
    fun on(cmd: DodgeCalculationCommand) {
        val attributes = cmd.damagedAttributes[CustomAttributeTypes.DODGE] ?: return

        for (attribute in attributes) {
            val dodge = attribute.rolls[0].asDoubleRoll().getValue()
            if (Random.nextDouble() > dodge) continue

            cmd.isDodging = true
            return
        }
    }
}