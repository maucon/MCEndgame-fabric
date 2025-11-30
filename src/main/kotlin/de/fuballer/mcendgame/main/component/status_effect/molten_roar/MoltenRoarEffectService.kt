package de.fuballer.mcendgame.main.component.status_effect.molten_roar

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.component.status_effect.CustomStatusEffects
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.LivingEntity

@Injectable
class MoltenRoarEffectService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        val damager = cmd.damager as? LivingEntity ?: return
        val amplifier = damager.getStatusEffect(CustomStatusEffects.MOLTEN_ROAR)?.amplifier ?: return
        cmd.moreDamage.add(0.1 + (amplifier * 0.05))
    }
}