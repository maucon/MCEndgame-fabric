package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.ApplyDamageCalculationCommand
import de.fuballer.mcendgame.main.component.damage.DodgeCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects
import kotlin.random.Random

@Injectable
class AttributesWhilePoisonedService {
    @CommandHandler
    fun on(cmd: ApplyDamageCalculationCommand) {
        offensive(cmd)
        defensive(cmd)
    }

    private fun offensive(cmd: ApplyDamageCalculationCommand) {
        val damager = cmd.damager as? LivingEntity ?: return
        if (!damager.hasStatusEffect(StatusEffects.POISON)) return

        var attributes = cmd.damagerAttributes[CustomAttributeTypes.INCREASED_DAMAGE_WHILE_POISONED] ?: return
        attributes.forEach { attribute ->
            val increasedDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.increasedDamage.add(increasedDamage)
        }

        attributes = cmd.damagerAttributes[CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE_WHILE_POISONED] ?: return
        attributes.forEach { attribute ->
            val increasedDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.increasedElementalDamage.add(increasedDamage)
        }
    }

    private fun defensive(cmd: ApplyDamageCalculationCommand) {
        if (!cmd.damaged.hasStatusEffect(StatusEffects.POISON)) return

        val attributes = cmd.damagedAttributes[CustomAttributeTypes.MORE_DAMAGE_TAKEN_WHILE_POISONED] ?: return
        attributes.forEach { attribute ->
            val moreDamage = attribute.rolls[0].asDoubleRoll().getActualRoll()
            cmd.moreDamageTaken.add(moreDamage)
        }
    }

    private fun dodge(cmd: DodgeCalculationCommand) {
        if (!cmd.damaged.hasStatusEffect(StatusEffects.POISON)) return

        val attributes = cmd.damagedAttributes[CustomAttributeTypes.DODGE_WHILE_POISONED] ?: return
        for (attribute in attributes) {
            val dodge = attribute.rolls[0].asDoubleRoll().getActualRoll()
            if (Random.nextDouble() > dodge) continue

            cmd.isDodging = true
            break
        }
    }
}