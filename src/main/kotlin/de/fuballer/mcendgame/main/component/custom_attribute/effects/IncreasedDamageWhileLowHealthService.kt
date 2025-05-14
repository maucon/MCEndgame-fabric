package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributeUtil.isLowHealth
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.LivingEntity

@Injectable
class IncreasedDamageWhileLowHealthService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val livingDamager = cmd.damager as? LivingEntity ?: return

        val attributes = cmd.damagerAttributes[CustomAttributeTypes.INCREASED_DAMAGE_WHILE_LOW_HEALTH] ?: return
        if (!livingDamager.isLowHealth()) return

        attributes.forEach { attribute ->
            val increasedDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.increasedDamage.add(increasedDamage)
        }
    }
}