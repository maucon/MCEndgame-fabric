package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class TwinfireMoreDamageService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        val attributes = cmd.damagerAttributes[CustomAttributeTypes.TWINFIRE_DUAL_WIELD_MORE_DAMAGE] ?: return
        if (attributes.size < 2) return

        attributes.forEach { attribute ->
            val moreDamage = attribute.rolls[0].asDoubleRoll().getValue()
            cmd.moreDamage.add(moreDamage)
        }
    }
}