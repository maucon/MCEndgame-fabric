package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.collectAttribute.CollectElementalDamageCommand
import de.fuballer.mcendgame.main.messaging.collectAttribute.CollectHealPowerCommand
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class HealPowerPerElementalDamageService {
    @CommandHandler
    fun on(cmd: CollectHealPowerCommand) {
        val attributes = cmd.attributes[CustomAttributeTypes.HEAL_POWER_PER_ELEMENTAL_DAMAGE] ?: return
        val percentageSum = attributes.sumOf { (it.rolls[0] as DoubleRoll).getValue() }

        val collectElementalCommand = CollectElementalDamageCommand(cmd.entity)
        val elementalDamage = CommandGateway.apply(collectElementalCommand).calculate()

        cmd.flat.add(percentageSum * elementalDamage)
    }
}