package de.fuballer.mcendgame.main.component.custom_attribute.effects.horn

import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.asDoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.CustomAttributesExtensions.getAllCustomAttributes
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.misc.horn.command.HornUseCommand
import de.maucon.mauconframework.command.CommandHandler
import de.maucon.mauconframework.di.annotation.Injectable

@Injectable
class MoreHornEffectDurationService {
    @CommandHandler
    fun on(cmd: HornUseCommand) {
        val attributes = cmd.user.getAllCustomAttributes()[CustomAttributeTypes.MORE_HORN_EFFECT_DURATION] ?: return
        attributes.forEach { cmd.moreDuration.add(it.rolls[0].asDoubleRoll().getValue()) }
    }
}