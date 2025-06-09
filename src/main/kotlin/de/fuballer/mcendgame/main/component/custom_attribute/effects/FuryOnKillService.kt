package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.status_effect.CustomStatusEffects
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDeathEvent
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.effect.StatusEffectInstance
import kotlin.math.min

@Injectable
class FuryOnKillService {
    @EventSubscriber
    fun on(event: LivingEntityDeathEvent) {
        val killer = event.killer ?: return
        val attributes = killer.getAllCustomAttributes()[CustomAttributeTypes.FURY_ON_KILL] ?: return

        val currentFury = killer.getStatusEffect(CustomStatusEffects.FURY)?.amplifier ?: -1
        val newFury = min(currentFury + attributes.size, 9)

        killer.addStatusEffect(StatusEffectInstance(CustomStatusEffects.FURY, 200, newFury, false, true, true))
    }
}