package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getHealingFactor
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.tags.CustomTags
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamagedEvent
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isAlly
import de.fuballer.mcendgame.main.util.extension.mixin.PlayerEntityMixinExtension.getAttackCooldownMultiplier
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity

@Injectable
class HealNearbyAlliesOnMeleeHitService {
    @EventSubscriber
    fun on(event: LivingEntityDamagedEvent) {
        if (!event.damageSource.isIn(CustomTags.MELEE_ATTACK)) return

        val attacker = event.damageSource.attacker as? LivingEntity ?: return

        val attributes = attacker.getAllCustomAttributes()[CustomAttributeTypes.HEAL_NEARBY_ALLIES_ON_MELEE_HIT] ?: return
        val attributesValues = attributes.groupBy { (it.rolls[0] as IntRoll).getValue() }
            .mapValues { (_, values) -> values.sumOf { (it.rolls[1] as DoubleRoll).getValue() } }

        var healFactor = attacker.getHealingFactor()
        healFactor *= (attacker as? PlayerEntity)?.getAttackCooldownMultiplier() ?: 1F

        attributesValues.forEach { range, baseHeal ->
            val allies = attacker.entityWorld.getEntitiesByClass(LivingEntity::class.java, attacker.boundingBox.expand(range.toDouble())) { attacker.isAlly(it) }
            val heal = (baseHeal * healFactor).toFloat()
            allies.forEach { it.heal(heal) }
        }
    }
}