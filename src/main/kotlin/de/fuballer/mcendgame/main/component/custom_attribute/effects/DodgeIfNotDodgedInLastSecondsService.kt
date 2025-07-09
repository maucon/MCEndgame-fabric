package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.accessor.LivingEntityDodgedRecentlyAccessor
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asIntRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.damage.DodgeCalculationCommand
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityDodgedEvent
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable
import de.maucon.mauconframework.event.EventSubscriber
import kotlin.random.Random

@Injectable
class DodgeIfNotDodgedInLastSecondsService {
    @CommandHandler
    fun on(cmd: DodgeCalculationCommand) {
        val attributes = cmd.damagedAttributes[CustomAttributeTypes.DODGE_IF_NOT_DODGED_IN_LAST_SECONDS] ?: return

        val accessor = cmd.damaged as LivingEntityDodgedRecentlyAccessor

        for (attribute in attributes) {
            val ticks = attribute.rolls[1].asIntRoll().getActualRoll() * 20
            if (accessor.`mcendgame$hasDodged`(ticks)) continue

            val dodge = attribute.rolls[0].asDoubleRoll().getActualRoll()
            if (Random.nextDouble() > dodge) continue

            cmd.isDodging = true
            return
        }
    }

    @EventSubscriber
    fun on(event: LivingEntityDodgedEvent) {
        val accessor = event.entity as LivingEntityDodgedRecentlyAccessor
        accessor.`mcendgame$updateDodge`()
    }
}