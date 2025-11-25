package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class MoreDamageTakenPerMaxHeartAboveTenService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        val attributes = cmd.damagedAttributes[CustomAttributeTypes.MORE_DAMAGE_TAKEN_PER_MAX_HEART_ABOVE_TEN] ?: return

        val extraHearts = (cmd.damaged.maxHealth / 2 - 10).toInt()
        for (attribute in attributes) {
            val moreDamageTaken = attribute.rolls[0].asDoubleRoll().getValue() * extraHearts
            cmd.moreDamageTaken.add(moreDamageTaken)
        }
    }
}