package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects

@Injectable
class AttributesWhileWitheredService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        offensive(cmd)
    }

    private fun offensive(cmd: ApplyDamageCalculationCommand) {
        val damager = cmd.damager as? LivingEntity ?: return
        if (!damager.hasStatusEffect(StatusEffects.WITHER)) return

        val attributes = cmd.damagerAttributes[CustomAttributeTypes.INCREASED_DAMAGE_WHILE_WITHERED] ?: return
        attributes.forEach { attribute ->
            val increasedDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.increasedDamage.add(increasedDamage)
        }
    }
}