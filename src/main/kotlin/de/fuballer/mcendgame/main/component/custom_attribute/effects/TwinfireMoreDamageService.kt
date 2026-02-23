package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttributeType
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.messaging.collect_attribute.CollectGenericMoreDamageCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class TwinfireMoreDamageService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        cmd.moreDamage.addAll(getMultipliers(cmd.damagerAttributes))
    }

    @CommandHandler
    fun on(cmd: CollectGenericMoreDamageCommand) {
        cmd.more.addAll(getMultipliers(cmd.attributes))
    }

    private fun getMultipliers(attributes: Map<CustomAttributeType, List<CustomAttribute>>): List<Double> {
        val attr = attributes[CustomAttributeTypes.TWINFIRE_DUAL_WIELD_MORE_DAMAGE] ?: return listOf()
        if (attr.size < 2) return listOf()

        return attr.map { it.rolls[0].asDoubleRoll().getValue() }
    }
}