package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.collectAttribute.CollectHealPowerCommand
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamagedEvent
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isAlly
import de.fuballer.mcendgame.main.util.extension.mixin.PlayerEntityMixinExtension.getAttackCooldownMultiplier
import de.maucon.mauconframework.command.CommandGateway
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity

@Injectable
class HealNearbyAlliesOnHitService {
    @EventSubscriber
    fun on(event: LivingEntityDamagedEvent) {
        val attacker = event.damageSource.attacker as? LivingEntity ?: return

        val attributes = attacker.getAllCustomAttributes()[CustomAttributeTypes.HEAL_NEARBY_ALLIES_ON_HIT] ?: return
        val attributesValues = attributes.groupBy { (it.rolls[0] as IntRoll).getValue() }
            .mapValues { (_, values) -> values.sumOf { (it.rolls[1] as DoubleRoll).getValue() } }

        val command = CollectHealPowerCommand(attacker)
        var healPower = CommandGateway.apply(command).calculate()

        healPower *= (attacker as? PlayerEntity)?.getAttackCooldownMultiplier() ?: 1F

        attributesValues.forEach { range, healPercentage ->
            val heal = (healPower * healPercentage).toFloat()
            val allies = attacker.world.getEntitiesByClass(LivingEntity::class.java, attacker.boundingBox.expand(range.toDouble())) { attacker.isAlly(it) }
            allies.forEach { it.heal(heal) }
        }
    }
}