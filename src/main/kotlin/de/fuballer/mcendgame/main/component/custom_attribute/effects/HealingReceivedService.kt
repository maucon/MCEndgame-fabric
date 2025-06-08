package de.fuballer.mcendgame.main.component.custom_attribute.effects

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.messaging.misc.LivingEntityHealCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class HealingReceivedService {
    @CommandHandler
    fun onIncrease(cmd: LivingEntityHealCommand) {
        val attributes = cmd.entity.getAllCustomAttributes()[CustomAttributeTypes.INCREASED_HEALING_RECEIVED] ?: return
        attributes.forEach {
            cmd.increase.add(it.rolls[0].asDoubleRoll().getActualRoll().toFloat())
        }
    }

    @CommandHandler
    fun onMore(cmd: LivingEntityHealCommand) {
        val attributes = cmd.entity.getAllCustomAttributes()[CustomAttributeTypes.MORE_HEALING_RECEIVED] ?: return
        attributes.forEach {
            cmd.more.add(it.rolls[0].asDoubleRoll().getActualRoll().toFloat())
        }
    }
}