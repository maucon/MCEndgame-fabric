package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributeUtil.isLowHealth
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.misc.ServerLivingEntityEndTickEvent
import de.fuballer.mcendgame.main.util.extension.EntityExtension.applyPeriodicEffectIfTicksPassed
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects

@Injectable
class ResistanceWhenLowHealthService {
    @EventSubscriber
    fun on(event: ServerLivingEntityEndTickEvent) {
        if (event.world.time % 5 != 0L) return

        val entity = event.entity
        if (!entity.isLowHealth()) return

        val attributes = entity.getAllCustomAttributes()[CustomAttributeTypes.RESISTANCE_WHEN_LOW_HEALTH] ?: return
        val duration = attributes.maxOf { it.rolls[0].asIntRoll().getValue() } * 20

        val effectInstance = StatusEffectInstance(StatusEffects.RESISTANCE, duration, 0, false, true, true)
        entity.applyPeriodicEffectIfTicksPassed(effectInstance)
    }
}