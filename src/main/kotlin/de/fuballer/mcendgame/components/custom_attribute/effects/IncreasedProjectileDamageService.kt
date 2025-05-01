package de.fuballer.mcendgame.components.custom_attribute.effects

import de.fuballer.mcendgame.components.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.components.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class IncreasedProjectileDamageService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val attributes = cmd.damagerAttributes[CustomAttributeTypes.INCREASED_PROJECTILE_DAMAGE] ?: return

        attributes.forEach { attribute ->
            val increaseProjectileDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.increasedProjectileDamage.add(increaseProjectileDamage)
        }
    }
}