package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttributeType
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DamageCalculationCommand
import de.fuballer.mcendgame.main.messaging.collect_attribute.CollectGenericIncreasedDamageCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects

@Injectable
class AttributesWhileWitheredService {
    @CommandHandler
    fun on(cmd: DamageCalculationCommand) {
        val damager = cmd.damager as? LivingEntity ?: return
        if (!damager.hasStatusEffect(StatusEffects.WITHER)) return
        cmd.increasedDamage.addAll(getDoubleValues(cmd.damagerAttributes, CustomAttributeTypes.INCREASED_DAMAGE_WHILE_WITHERED))
    }

    @CommandHandler
    fun on(cmd: CollectGenericIncreasedDamageCommand) {
        if (!cmd.entity.hasStatusEffect(StatusEffects.WITHER)) return
        cmd.increased.addAll(getDoubleValues(cmd.attributes, CustomAttributeTypes.INCREASED_DAMAGE_WHILE_WITHERED))
    }

    private fun getDoubleValues(
        attributes: Map<CustomAttributeType, List<CustomAttribute>>,
        attributeType: CustomAttributeType,
    ): List<Double> {
        val attr = attributes[attributeType] ?: return listOf()
        return attr.map { it.rolls[0].asDoubleRoll().getValue() }
    }
}