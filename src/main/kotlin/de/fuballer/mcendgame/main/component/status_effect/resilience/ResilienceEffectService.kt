package de.fuballer.mcendgame.main.component.status_effect.resilience

import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.fuballer.mcendgame.main.component.status_effect.CustomStatusEffects
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class ResilienceEffectService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val amplifier = cmd.damaged.getStatusEffect(CustomStatusEffects.RESILIENCE)?.amplifier ?: return

        cmd.lessDamage.add((amplifier + 1) * 0.05)
    }
}