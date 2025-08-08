package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.LivingEntity

@Injectable
class MoreDamagePerMissingHeartService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        val attributes = cmd.damagerAttributes[CustomAttributeTypes.MORE_DAMAGE_PER_MISSING_HEART] ?: return

        val livingDamager = cmd.damager as? LivingEntity ?: return
        val missingHearts = (livingDamager.maxHealth - livingDamager.health) / 2

        attributes.forEach { attribute ->
            val moreDamagePerMissingHeart = attribute.rolls[0].asDoubleRoll().getValue()
            val moreDamage = moreDamagePerMissingHeart * missingHearts
            cmd.moreDamage.add(moreDamage)
        }
    }
}