package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes

@Injectable
class MovementSpeedModifiersAffectDamageService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        val damager = cmd.damager as? LivingEntity ?: return

        val attributes = cmd.damagerAttributes[CustomAttributeTypes.MOVEMENT_SPEED_MODIFIERS_AFFECT_DAMAGE] ?: return
        val effectiveness = attributes.sumOf { it.rolls[0].asDoubleRoll().getValue() }

        val movementSpeedInstance = damager.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED) ?: return
        movementSpeedInstance.modifiers.forEach { modifier ->
            when (modifier.operation) {
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE -> {
                    val increase = modifier.value * effectiveness
                    cmd.increasedDamage.add(increase)
                }

                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL -> {
                    val more = modifier.value * effectiveness
                    cmd.moreDamage.add(more)
                }

                else -> {}
            }
        }
    }
}