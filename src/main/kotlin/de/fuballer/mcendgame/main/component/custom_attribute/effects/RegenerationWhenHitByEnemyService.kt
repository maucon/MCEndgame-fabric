package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamagedEvent
import de.fuballer.mcendgame.main.util.extension.EntityExtension.isEnemy
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects

@Injectable
class RegenerationWhenHitByEnemyService {
    @EventSubscriber
    fun on(event: LivingEntityDamagedEvent) {
        val damaged = event.damaged
        val attacker = event.damageSource.attacker ?: return
        if (!damaged.isEnemy(attacker)) return

        val attributes = damaged.getAllCustomAttributes()[CustomAttributeTypes.REGENERATION_WHEN_HIT_BY_ENEMY] ?: return

        attributes.forEach {
            val duration = it.rolls[1].asIntRoll().getValue() * 20
            val amplifier = it.rolls[0].asIntRoll().getValue() - 1
            val effectInstance = StatusEffectInstance(StatusEffects.REGENERATION, duration, amplifier, false, true, true)
            damaged.addStatusEffect(effectInstance)
        }
    }
}