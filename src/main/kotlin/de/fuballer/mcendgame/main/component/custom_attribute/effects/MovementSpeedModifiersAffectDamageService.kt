package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttributeType
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.messaging.collect_attribute.CollectGenericIncreasedAndMoreDamageCommand
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
        val factors = getFactors(damager, cmd.damagerAttributes)
        factors[EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE]?.let { cmd.increasedDamage.addAll(it) }
        factors[EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL]?.let { cmd.moreDamage.addAll(it) }
    }

    @CommandHandler
    fun on(cmd: CollectGenericIncreasedAndMoreDamageCommand) {
        val factors = getFactors(cmd.entity, cmd.attributes)
        factors[EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE]?.let { cmd.increased.addAll(it) }
        factors[EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL]?.let { cmd.more.addAll(it) }
    }

    private fun getFactors(
        entity: LivingEntity,
        customAttributes: Map<CustomAttributeType, List<CustomAttribute>>,
    ): Map<EntityAttributeModifier.Operation, List<Double>> {
        val attr = customAttributes[CustomAttributeTypes.MOVEMENT_SPEED_MODIFIERS_AFFECT_DAMAGE] ?: return mapOf()
        val effectiveness = attr.sumOf { it.rolls[0].asDoubleRoll().getValue() }

        val movementSpeedInstance = entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED) ?: return mapOf()
        return movementSpeedInstance.modifiers
            .filter { it.operation == EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE || it.operation == EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL }
            .groupBy { it.operation }
            .mapValues { (_, modifierList) -> modifierList.map { it.value * effectiveness } }
    }
}