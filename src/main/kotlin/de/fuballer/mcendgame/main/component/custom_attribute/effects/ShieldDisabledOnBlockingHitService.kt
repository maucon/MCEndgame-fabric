package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.misc.ShieldHitEvent
import de.fuballer.mcendgame.main.util.extension.EntityExtension.setShieldsCooldown
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber

@Injectable
class ShieldDisabledOnBlockingHitService {
    @EventSubscriber(sync = true)
    fun on(event: ShieldHitEvent) {
        val attributes = event.entity.getAllCustomAttributes()[CustomAttributeTypes.SHIELD_DISABLED_ON_BLOCKING_HIT]
        val cooldown = attributes?.maxOfOrNull { (it.rolls[0] as IntRoll).getValue() } ?: return
        event.entity.setShieldsCooldown(cooldown.toFloat())
    }
}