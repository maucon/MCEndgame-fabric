package de.fuballer.mcendgame.main.component.status_effect.verdant_echo

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.component.status_effect.CustomStatusEffects
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class VerdantEchoEffectService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        val amplifier = cmd.damaged.getStatusEffect(CustomStatusEffects.VERDANT_ECHO)?.amplifier ?: return
        cmd.moreDamageTaken.add(-0.1 + (amplifier * -0.05))
    }
}