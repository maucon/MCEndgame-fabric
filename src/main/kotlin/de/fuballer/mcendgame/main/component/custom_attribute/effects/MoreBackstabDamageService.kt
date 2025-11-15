package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isBehind
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class MoreBackstabDamageService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        val attributes = cmd.damagerAttributes[CustomAttributeTypes.MORE_BACKSTAB_DAMAGE] ?: return

        val damager = cmd.damager ?: return
        if (!damager.isBehind(cmd.damaged)) return

        attributes.forEach { attribute ->
            val moreDamage = attribute.rolls[0].asDoubleRoll().getValue()
            cmd.moreDamage.add(moreDamage)
        }
    }
}