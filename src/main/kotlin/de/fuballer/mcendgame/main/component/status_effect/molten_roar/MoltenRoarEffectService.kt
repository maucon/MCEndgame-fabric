package de.fuballer.mcendgame.main.component.status_effect.molten_roar

import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.component.status_effect.CustomStatusEffects
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.LivingEntity

private const val BASE_MORE_DAMAGE = 0.1
private const val MORE_DAMAGE_PER_AMPLIFIER = 0.05

@Injectable
class MoltenRoarEffectService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        val damager = cmd.damager as? LivingEntity ?: return
        val amplifier = damager.getStatusEffect(CustomStatusEffects.MOLTEN_ROAR)?.amplifier ?: return
        cmd.moreDamage.add(BASE_MORE_DAMAGE + (amplifier * MORE_DAMAGE_PER_AMPLIFIER))
    }
}