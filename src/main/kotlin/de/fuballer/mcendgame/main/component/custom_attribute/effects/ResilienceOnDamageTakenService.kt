package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.status_effect.CustomStatusEffects
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDamagedEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.effect.StatusEffectInstance
import kotlin.math.min

@Injectable
class ResilienceOnDamageTakenService {
    @EventSubscriber
    fun on(event: LivingEntityDamagedEvent) {
        if (event.amount <= 0) return

        val damaged = event.damaged
        val attributes = damaged.getAllCustomAttributes()[CustomAttributeTypes.RESILIENCE_ON_DAMAGE_TAKEN] ?: return

        val currentResilience = damaged.getStatusEffect(CustomStatusEffects.RESILIENCE)?.amplifier ?: -1
        val newResilience = min(currentResilience + attributes.size, 9)

        damaged.addStatusEffect(StatusEffectInstance(CustomStatusEffects.RESILIENCE, 199, newResilience, false, true, true))
    }
}