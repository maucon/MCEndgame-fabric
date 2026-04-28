package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class NoAttackDamageService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        cmd.damagerAttributes[CustomAttributeTypes.NO_ATTACK_DAMAGE] ?: return
        cmd.moreAttackDamage.add(-1.0)
    }
}